package webmvc.config;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MyView extends AbstractView {

    private Integer statusCode;


    public MyView(Integer statusCode){
        this.statusCode = statusCode;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setStatus(this.statusCode);

    }
}
