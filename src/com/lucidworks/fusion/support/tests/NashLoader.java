package com.lucidworks.fusion.support.tests;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import jdk.nashorn.api.scripting.JSObject;



public class NashLoader {
    private ScriptEngineManager scriptManager;
    private ScriptEngine engine;
 
    public NashLoader(){
        scriptManager = new ScriptEngineManager();
        engine = scriptManager.getEngineByName("nashorn");
    }
public void putJavaVariablesIntoEngine(List variables) {
 
    Bindings bindings = new SimpleBindings();
 
    for (Object variable : variables) {
        putJavaVariableIntoEcmaScope(bindings, variable, variable.getClass().getCanonicalName());
    }
 
    try{
     JSObject multiply = (JSObject) engine.eval("function(x, y) { return x*y; }");
    }catch(Exception e){
        e.printStackTrace();
    }
     
    engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
}
 
private void putJavaVariableIntoEcmaScope(Bindings bindings,
        Object variable, String variableName) {
 
    //String variableName = variable.getName();
   // EcmaValue ecmaValue = variable.getValue();
  //  Object javaValue = ecmaValue.getValue();
    System.out.println("Bind: "+variableName);
    bindings.put(variableName, variable);
}

public static void main(String[] args){
    NashLoader loader = new NashLoader();
    List list = new ArrayList();
    list.add(new String("hell0"));
    list.add(new Integer(9));
    list.add(new Date());
    list.add(Calendar.getInstance());
    
     loader.putJavaVariablesIntoEngine(list);
    
}

}
