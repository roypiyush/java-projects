package com.personal.serialization;

import java.io.ObjectStreamException;
import java.io.Serializable;

@SuppressWarnings("unused")
public class SerializableSubclass extends NonSerializableSuperClass implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String staticString;

	private String name;
	private String value;

	public SerializableSubclass() {
		super();
	}

	/*
	 * Use this method to override default serialization process. If
	 * implemented, then this class is responsible for writing object's state
	 */
	/*
	 * private void writeObject(java.io.ObjectOutputStream out) throws
	 * IOException { out.defaultWriteObject(); }
	 */

	/*
	 * Use this method to override default de-serialization process If
	 * implemented, then this class is responsible for reading object's state
	 */
	/*
	 * private void readObject(java.io.ObjectInputStream in) throws IOException,
	 * ClassNotFoundException {
	 * 
	 * }
	 */

	/*
	 * The readObject method is responsible for reading from the stream and
	 * restoring the classes fields. It may call in.defaultReadObject to invoke
	 * the default mechanism for restoring the object's non-static and
	 * non-transient fields. The defaultReadObject method uses information in
	 * the stream to assign the fields of the object saved in the stream with
	 * the correspondingly named fields in the current object. This handles the
	 * case when the class has evolved to add new fields. The method does not
	 * need to concern itself with the state belonging to its superclasses or
	 * subclasses. State is saved by writing the individual fields to the
	 * ObjectOutputStream using the writeObject method or by using the methods
	 * for primitive data types supported by DataOutput.
	 */
	private void readObjectNoData() throws ObjectStreamException {

	}

	/*
	 * Serializable classes that need to designate an alternative object to be
	 * used when writing an object to the stream should implement this special
	 * method with the exact signature:
	 */
	// Typically any access modifier
	Object writeReplace() throws ObjectStreamException {
		return this;
	}

	/*
	 * Classes that need to designate a replacement when an instance of it is
	 * read from the stream should implement this special method with the exact
	 * signature.
	 */
	// Typically any access modifier
	Object readResolve() throws ObjectStreamException {
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		staticString = "staticSubclassString";
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SerializableSubclass other = (SerializableSubclass) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " " + value + " " + hashCode();
	}
}
