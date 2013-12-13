package org.jpc.examples.metro.model.imp;

import static java.util.Arrays.asList;
import static org.jpc.engine.provider.PrologEngineProviderManager.getPrologEngine;

import java.util.ArrayList;
import java.util.List;

import org.jpc.Jpc;
import org.jpc.JpcBuilder;
import org.jpc.engine.logtalk.LogtalkObject;
import org.jpc.examples.metro.model.Line;
import org.jpc.examples.metro.model.Metro;
import org.jpc.examples.metro.model.jpcconverters.LineConverter;
import org.jpc.examples.metro.model.jpcconverters.MetroConverter;
import org.jpc.examples.metro.model.jpcconverters.StationConverter;
import org.jpc.query.Query;
import org.jpc.query.Solution;
import org.jpc.term.Atom;
import org.jpc.term.Compound;
import org.jpc.term.Term;
import org.jpc.term.Var;

public class MetroJpc implements Metro {

	public static final Jpc jpcContext = JpcBuilder.create()
		.register(new MetroConverter())
		.register(new LineConverter())
		.register(new StationConverter()).build();

	
	@Override
	public String toString() {return "metro";}

	private LogtalkObject asLogtalkObject() {
		return new LogtalkObject(this, getPrologEngine(), jpcContext);
	}
	
	@Override
	public List<Line> lines() {
		String lineVarName = "Line";
		Term message = new Compound(LineConverter.LINE_FUNCTOR, asList(new Var(lineVarName)));
		Query query = asLogtalkObject().perform(message);
		return query.<Line>selectObject().allSolutions();
	}

	public List<Line> lines_PADLpaper() {
		String lineVarName = "Line";
		Term message = new Compound(LineConverter.LINE_FUNCTOR, asList(new Var(lineVarName)));
		Query query = asLogtalkObject().perform(message);
		List<Solution> solutions = query.allSolutions();
		List<Line> lines = new ArrayList<>();
		for(Solution solution : solutions) {
			Compound lineTerm = (Compound)solution.get(lineVarName);
			Line line = new LineJpc(((Atom)lineTerm.arg(1)).getName());
			lines.add(line);
		}
		return lines;
	}
	
	@Override
	public Line line(String name) {
		Term message = new Compound(LineConverter.LINE_FUNCTOR, asList(new Atom(name)));
		return asLogtalkObject().perform(message).<Line>selectObject().oneSolutionOrThrow();
	}

}
