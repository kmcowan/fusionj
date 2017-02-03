/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function require(classes, callback) {
    try {
        var Class = java.lang.Class;
        var e = java.lang.ClassNotFoundException;
        var engine = javax.script.ScriptEngine;
        var scriptManager = javax.script.ScriptEngineManager;
        var bindings = javax.script.SimpleBindings;
        var ScriptContext = javax.script.ScriptContext;
        var JSObject = Java.type("jdk.nashorn.api.scripting.JSObject");
        var fnc = Java.type("jdk.nashorn.api.scripting.JSObject");
        var System = java.lang.System;

        bindings = new javax.script.SimpleBindings();

        var cls = "";
        var clsName = "";

        for (var i = 0; i < classes.length; i++) {
            cls = classes[i];
            clsName = cls.substring(cls.lastIndexOf(".") + 1, cls.length);
            System.out.println("Class: " + cls + " Name: " + clsName);
            Class.forName(cls);
            bindings.put(clsName, Java.type(cls));
        }

        scriptManager = new javax.script.ScriptEngineManager();
        engine = scriptManager.getEngineByName("nashorn");
        // bindings.put("System", java.lang.System);
        engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
        scriptManager.setBindings(bindings);


        if (callback !== null) {
            fnc = engine.eval(callback);
            fnc.call();
        }
    } catch (e) {
        //my class isn't there!
        e.printStackTrace();
    }
}


require(["javax.script.ScriptEngine", 
    "java.util.ArrayList", 
    "java.lang.System"], function () {
    System.out.println(new Date().toString());
});


