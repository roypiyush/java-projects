/**
 * 
 */
package com.personal.designpatterns.vistitor;


/**
 * Represents type of Object that needs to <b>accept</b> a visitor. This acceptance
 * allows visitor to perform operations on <b>this</b> object.
 * 
 * @author piyush
 *
 */
public interface ICarElement {
	void accept(Visitor visitor);
}
