import java.lang.reflect.*;
/**
 * This class tests the ParkingLot and LFHSTest
 * 
 * @author Mr. Aronson
 */
public class ParkingLotTest
{
    private String className = "ParkingLot";
    private  boolean failed = false;
    private  Object t1, t2, t3;
    private  Class<?> c, c2;
    private Object[] cArgs = {70};
    private Object[] cArgs2 = {};
    private Constructor constructor, constructor2;

    public static void main(String args[])
    {
        ParkingLotTest p = new ParkingLotTest();
        p.test();
    }

    public void test() {
        //********** ParkingLot Class Test **************************************
        // Instantiate a new ParkingLot object
        System.out.println("Now testing your ParkingLot class: \n");
        try
        {

            c = Class.forName(className);
        }
        catch (NoClassDefFoundError e)
        {
            failure("Epic Failure: missing ParkingLot class");
        }
        catch (ClassNotFoundException e)
        {
            failure("Epic Failure: missing ParkingLot class");
        }
        catch(Exception e) {
            failure(e.toString());}

        int numDoubleFields = 0;
        int numIntFields = 1;
        boolean numCarsSet = false;
        boolean maxCarsSet = false;
        System.out.println("Testing instance variables and constructor");
        if(!failed)
        {
            Field[] fields = c.getDeclaredFields();
            if(fields.length == 0)
                failure("ParkingLot has no instance variables");
            else
            {
                for(Field field : fields)
                {
                    String temp = field.toString();
                    if (temp.contains("numCars") && !temp.contains("int"))
                        failure("numCars instance variable is not an int");
                    else if (temp.contains("numCars") && !temp.contains("private"))
                        failure("numCars instance variable is not private");
                    else if (temp.contains("private") && temp.contains("numCars") && temp.contains("int"))
                    {
                        try
                        {
                            Constructor defaultConstructor = c.getConstructor();
                            Object tempObj = defaultConstructor.newInstance();
                            field.setAccessible(true);
                            int val = (int)field.getInt(tempObj);
                            if ( val == 0)
                                numCarsSet = true;
                            else
                                failure("default constructor setting numCars to " + val + " but should be 0");
                        }
                        catch (NoSuchMethodException e)
                        {
                            failure("missing default constructor ParkingLot()");
                        }
                        catch (Exception e)
                        {
                            failure(e.toString());
                        }
                        if (!failed) {
                            try
                            {

                                constructor = c.getConstructor(new Class[] {int.class});
                                t1 = constructor.newInstance(cArgs);

                                field.setAccessible(true);
                                int val = (int)field.getInt(t1);
                                if (val == (int)cArgs[0])
                                    numCarsSet = true;
                                else
                                    failure("constructor with parametersetting numCars to " + val + " but should be " + cArgs[0]);
                            }
                            catch (NoSuchMethodException e)
                            {
                                failure("missing constructor ParkingLot(int numCars)");
                            }

                            catch (Exception e)
                            {
                                failure(e.toString());
                            }
                        }
                    }
                    else if (!temp.contains("final") && temp.contains("MAX_CARS"))
                        failure("MAX_CARS needs to be final since it is a constance");
                    else if (!temp.contains("int") && temp.contains("MAX_CARS"))
                        failure("MAX_CARS needs to be an int");
                    else if (!temp.contains("public") && temp.contains("MAX_CARS"))
                        failure("MAX_CARS should be a public constant");

                    else if (temp.contains("final") && temp.contains("int") && temp.contains("MAX_CARS"))
                    {

                        try
                        {
                            field.setAccessible(true);
                            if ((int)field.getInt(t1)== 2500)
                                maxCarsSet = true;
                            else
                                failure("MAX_CARS is not 2500");
                        }
                        catch (IllegalAccessException e)
                        {
                            failure("illegal access exception");
                        }
                    }

                }
            }
        }

        if(!failed && !numCarsSet)
            failure("instance variable numCars not set to constructor's num cars parameter");

        if(!failed && !maxCarsSet)
            failure("variable MAX_CARS needs to be final and set to 2500");

        if(!failed)
            System.out.println("Passed instance variable and constructor parameter test\n");

        if(!failed)
        {
            // Test getNumCars method
            System.out.println("Now testing getNumCars method");
            try
            {
                Method m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(t1);
                if(tempNum != 70)
                    failure("The numCars is " + tempNum +  " and should be " + cArgs[0]);
            } catch (NoSuchMethodException e)
            {
                failure("missing getNumCars() method");
            }
            catch(Exception e) {failure(e.toString());}
        }
        if(!failed)
            System.out.println("Passed getNumCars method test\n");

        // Test setTime method
        if(!failed)
        {
            System.out.println("Now testing setNumCars method");
            //      t2 = new ParkingLot(80);
            try
            {
                t2 = constructor.newInstance(cArgs);
                Method m = c.getDeclaredMethod("setNumCars", int.class);
                m.invoke(t2, 90);
                m = c.getDeclaredMethod("getNumCars");
                Object tempNum = m.invoke(t2);
            }
            catch (NoSuchMethodException e)
            {
                failure("missing setNumCars(int theNum) method");
            }
            catch(Exception e) {failure(e.toString());}
        }

        if(!failed)
        {
            try {
                Method m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(t2);
                if (tempNum != 90)
                    failure("setNumCars is " + tempNum + " but should be 90");
            }catch(Exception e) {failure(e.toString());}
        }

        if(!failed)
            System.out.println("Passed setNumCars method test\n");

        // Test error checks for setNumCars method
        if(!failed)
        {
            System.out.println("Now testing if setNumCars error checks");
            try
            {
                Object obj = constructor.newInstance(cArgs);
                Method m = c.getDeclaredMethod("setNumCars", int.class);
                m.invoke(obj, 80000);
                m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(obj);

                if (tempNum != 70)
                    failure("setNumCars does not error check for the MAX_CARS");
            }
            catch (NoSuchMethodException e)
            {
                failure("missing setNumCars() method");
            }
            catch(Exception e) {failure(e.toString());}
        }
        if(!failed)
        {

            try
            {
                Method m = c.getDeclaredMethod("setNumCars", int.class);
                m.invoke(t2, -3);
                m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(t2);

                if (tempNum < 0)
                    failure("setNumCars does not error check for negative number of cars");
            }
            catch (NoSuchMethodException e)
            {
                failure("missing setNumCars() method");
            }
            catch(Exception e) {failure(e.toString());}
        }
        if (!failed)
            System.out.println("Passed if setNumCars error checks\n");
        // Test for proper toString() method format
        if(!failed)
        {
            System.out.println("Now testing toString method");
            String objectToString = t1.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(t1));
            if(t1.toString().equals(objectToString))
                failure("missing toString method");
        }

        if(!failed)
        {
            if(!t1.toString().contains("Number of cars is "+cArgs[0]))
                failure("" + t1.toString() + " is an invalid toString format.  Should be for \"Number of cars is "+cArgs[0] +"\"");
        }

        if(!failed)
            System.out.println("Passed toString method test\n");

        if(!failed)
        {
            System.out.println("Congratulations, your ParkingLot class works correctly \n");
            System.out.println("****************************************************\n");
        }

        //********** LFHS Class Test **************************************
        // Instantiate a new LHFS object
        if (!failed)
        {
            System.out.println("Now testing your LFHS class: \n");
        }

        if (!failed)
        {
            try
            {
                c2 = Class.forName("LFHS");
                constructor2 = c2.getConstructor(new Class[] {});
                t3 = constructor2.newInstance(cArgs2);

            }
            catch (NoClassDefFoundError e)
            {
                failure("Epic Failure: missing LFHS class");
            }
            catch (ClassNotFoundException e)
            {
                failure("Epic Failure: missing LFHS class");
            }
            catch (Exception e) { failure(e.toString());}
        }

        if(!failed)
        {
            System.out.println("Now testing fillLot method");
            try
            {
                Method m = c2.getDeclaredMethod("fillLot");
                Object tempPL = m.invoke(t3);
                Method m2 = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m2.invoke(tempPL);

                if(tempNum != 400)

                    failure("The number of cars is " + tempNum + " but should be 400");
            }
            catch (NoSuchMethodException e)
            {
                failure("missing fillLot method");
            }
            catch (Exception e) { failure(e.toString());}
        }
        if (!failed)
        System.out.println("Passed fillLot method\n");


        if(!failed)
        {
            System.out.println("Congratulations, your LHFS class works correctly \n");
            System.out.println("****************************************************\n");
        }

        if(!failed)
            System.out.println("Yay! You have successfully completed the ParkingLot Project!");
        else
            System.out.println("\nBummer.  Try again.");
    }

    private void failure(String str)
    {
        System.out.println("*** Failed: " + str);
        failed = true;
    }

}
