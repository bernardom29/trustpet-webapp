/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: bernardo(Universidade do Minho)
 * License Type: Academic
 */
package main;

public class Dia {
	public Dia() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_DIA_HORAS) {
			return ORM_horas;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int id;
	
	private int dia;
	
	private java.util.Set ORM_horas = new java.util.HashSet();
	
	public void setDia(int value) {
		this.dia = value;
	}
	
	public int getDia() {
		return dia;
	}
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	private void setORM_Horas(java.util.Set value) {
		this.ORM_horas = value;
	}
	
	private java.util.Set getORM_Horas() {
		return ORM_horas;
	}
	
	public final main.HoraSetCollection horas = new main.HoraSetCollection(this, _ormAdapter, ORMConstants.KEY_DIA_HORAS, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
