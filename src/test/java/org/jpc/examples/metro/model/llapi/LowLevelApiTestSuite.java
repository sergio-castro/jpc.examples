package org.jpc.examples.metro.model.llapi;

import static org.junit.Assert.assertTrue;

import org.jpc.examples.metro.MetroExample;
import org.jpc.examples.metro.model.AllMetroTests;
import org.jpc.examples.metro.model.llapi.MetroFactoryLLApi;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AllMetroTests.class})
public class LowLevelApiTestSuite {
	@BeforeClass
    public static void oneTimeSetUp() {
		MetroExample.setFactory(new MetroFactoryLLApi());
		assertTrue(MetroExample.loadAll()); //load logic files
		MetroExample.removeData();
		MetroExample.importFromPrologFile(); //import data to the logic database from text file
    }
}
