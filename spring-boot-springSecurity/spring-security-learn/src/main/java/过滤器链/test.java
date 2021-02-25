package 过滤器链;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class test {
//    FilterSecurityInterceptor

//    DelegatingFilterProxy 伪代码
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
//        Filter delegate = (WebApplicationContext)wac.getBean(targetBeanName, Filter.class);
//        delegate.doFilter(request, response);
//    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        List<String> c = a.stream().filter(i -> i == "123").collect(Collectors.toList());
        c.forEach(i->System.out.println(i));
    }

}
