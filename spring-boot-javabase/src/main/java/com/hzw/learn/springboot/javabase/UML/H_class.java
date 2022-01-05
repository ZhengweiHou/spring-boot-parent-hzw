package com.hzw.learn.springboot.javabase.UML;

import static com.hzw.learn.springboot.javabase.UML.H_utils.h_utils_static_method;

public class H_class extends H_abstract implements H_interface{
    private H_filed h_private = new H_filed();
    H_filed h_default = new H_filed();
    protected H_filed h_protected = new H_filed();
    public H_filed h_public = new H_filed();

    public void sayHello(){
        h_private.sayHello();

        h_utils_static_method();
    }
}
