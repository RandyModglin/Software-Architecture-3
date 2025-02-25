import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class KWICObjectLoader extends ClassLoader {

	public Object loadObject(String className) {
     try {
            // Create a new ClassLoader 
            ClassLoader loader = this.getClass().getClassLoader();
		
            // Load the target class using its name
            @SuppressWarnings("rawtypes")
			Class aClass = loader.loadClass(className);

            // Create a new instance from the loaded class
            @SuppressWarnings({ "rawtypes", "unchecked" })
			Constructor constructor = aClass.getConstructor();
            Object obj = constructor.newInstance();

            //return the instance
            return obj;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}