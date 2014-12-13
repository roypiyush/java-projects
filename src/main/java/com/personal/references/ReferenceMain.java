package com.personal.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import com.personal.model.MySimpleJavaBean;

public class ReferenceMain {

	public static void main(String[] args) {

			
		MySimpleJavaBean bean = new MySimpleJavaBean();
		SoftReference<MySimpleJavaBean> softReference = new SoftReference<MySimpleJavaBean>(bean);
		System.out.println(softReference.get());
		softReference.clear();
		
		WeakReference<MySimpleJavaBean> weakReference = new WeakReference<MySimpleJavaBean>(bean);
		System.out.println(weakReference.get());
		weakReference.clear();
		
		
		PhantomReference<MySimpleJavaBean> phantomReference = new PhantomReference<MySimpleJavaBean>(bean, new ReferenceQueue<MySimpleJavaBean>());
		System.out.println("get() is always return null as referents are always inaccessible " + phantomReference.get() + " " + bean);
		phantomReference.clear();
		
		
		
	}

}
