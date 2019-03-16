package webmvc.config;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MyViewResolverImpl implements ViewResolver, MyViewResolver {

    private String prefix;
    private String suffix;


    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    private Queue<File> files;
    private File findedFile;

    public MyViewResolverImpl() {
        this.files = new ArrayDeque<>();
    }


    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path. Are you sure the package '%s' exists?";


    private File getView(String viewName) {
        String[] viewFolders = viewName.split("/");
        String viewFolder = "";
        if (viewFolders.length > 1) {
            viewFolder = viewFolders[viewFolders.length - 1];
        }
        String viewFolderPath = this.prefix.concat(viewFolder);
        URL scannedUrl = null;
        try {

            scannedUrl = new URL(new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath().split("/WEB-INF/classes")[0]).toURI().toURL().toString().concat(viewFolderPath));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedUrl));
        }

        File scannedDir = new File(scannedUrl.getFile());
        System.out.println(Arrays.toString(scannedDir.listFiles()));
        try {
            for (File file : Objects.requireNonNull(scannedDir.listFiles())) {
                this.find(file);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedUrl));
        }

        return this.findedFile;
    }

    private void find(File file) {

        String resource = this.prefix + file.getName();
        if (file.isDirectory()) {
            this.files.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
            while (this.files.size() > 0) {
                this.find(this.files.poll());
            }
        } else if (resource.endsWith(this.suffix)) {
            if (this.findedFile == null) {
                this.findedFile = file;
            }
        }

    }


    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        String forwardUrl;
        try{
            if (s.length()==3){
                int statusCode = Integer.valueOf(s);
                MyView myView = new MyView(statusCode);
                return myView;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        View view = null;
        if (s.startsWith("redirect:")) {
            forwardUrl = s.substring("redirect:".length());
            view = new RedirectView(Objects.requireNonNull(this.getView(forwardUrl)).toURI().toURL().toString());
        } else if (s.startsWith("forward:")) {
            forwardUrl = s.substring("forward:".length());
            view = new InternalResourceView(this.getView(forwardUrl).toURI().toURL().toString());
        } else {
            view = new InternalResourceView(this.getView(s).toURI().toURL().toString());
        }

        return view;

    }
}

