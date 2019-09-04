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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class HoraDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression hora;
	
	public HoraDetachedCriteria() {
		super(main.Hora.class, main.HoraCriteria.class);
		hora = new IntegerExpression("hora", this.getDetachedCriteria());
	}
	
	public HoraDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, main.HoraCriteria.class);
		hora = new IntegerExpression("hora", this.getDetachedCriteria());
	}
	
	public Hora uniqueHora(PersistentSession session) {
		return (Hora) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Hora[] listHora(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Hora[]) list.toArray(new Hora[list.size()]);
	}
}

