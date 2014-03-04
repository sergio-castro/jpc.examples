package org.jpc.examples.metro.model.hlapi;

import static java.util.Arrays.asList;
import static org.jpc.engine.provider.PrologEngineProviderManager.getPrologEngine;
import static org.jpc.examples.metro.model.hlapi.MetroHLApi.jpcContext;
import static org.jpc.term.Var.ANONYMOUS_VAR;

import org.jpc.engine.logtalk.LogtalkObject;
import org.jpc.examples.metro.model.Line;
import org.jpc.examples.metro.model.Station;
import org.jpc.query.Query;
import org.jpc.term.Compound;
import org.jpc.term.Term;

public class LineHLApi implements Line {

	private String name;
	
	public LineHLApi(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {return name;}

	
	private LogtalkObject<Compound> asLogtalkObject() {
		return new LogtalkObject<>(this, getPrologEngine(), jpcContext);
	}
	
	@Override
	public boolean connects(Station s1, Station s2) {
		Term message = jpcContext.toCompound("connects", asList(s1,s2));
		Query query = asLogtalkObject().perform(message);
		return query.hasSolution();
	}

	@Override
	public long segments() {
		Term message = new Compound("connects", asList(ANONYMOUS_VAR, ANONYMOUS_VAR));
		Query query = asLogtalkObject().perform(message);
		return query.numberOfSolutions();
	}
	
}