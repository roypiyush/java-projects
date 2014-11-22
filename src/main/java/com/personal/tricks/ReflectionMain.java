package com.personal.tricks;


public class ReflectionMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		try {

			Class<?> class1 = Class.forName("com.personal.tricks.BitOperations");
			System.out.println(class1.getCanonicalName());

			Object object = class1.newInstance();
			BitOperations operations = (BitOperations) object;
			operations.main(null);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
