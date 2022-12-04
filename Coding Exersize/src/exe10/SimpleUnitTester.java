package exe10;

import java.lang.reflect.Method;

public class SimpleUnitTester {
	 @SuppressWarnings("unchecked")
	public int execute(Class clazz) throws Exception {
	        int failedCount = 0;
	        
	        // your code
	        Reflection refObj = (Reflection) clazz.getConstructor().newInstance();
	        Method[] refMethods = clazz.getMethods();
	        for(Method m : refMethods) {
	        	if(m.getName().startsWith("test") && m.getReturnType() == boolean.class) {
	        		boolean result = (boolean) m.invoke(refObj, null);
	        		if(result == false)
	        			failedCount++;
	        	}
	        }
	        return failedCount;
	    }
}
