/**
 * 
 */
package com.personal.old.designpatterns.vistitor;


/**
 * Represents type of Object that needs to <b>accept</b> a visitor. This acceptance
 * allows visitor to perform operations on <b>this</b> object.
 * 
 * @author piyush
 *
 */
public interface ICarElement {
	/**
	 * Will accept Visitor that is passed.
	 * 
	 * @param visitor
	 */
	void accept(IVisitor visitor);
}
