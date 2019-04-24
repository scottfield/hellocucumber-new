package com.jackiew;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.Runtime;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class CucumberStaticRunner {
    public static byte startTests(String[] argv) throws Throwable {
        return run(argv, Thread.currentThread().getContextClassLoader());
    }

    public static byte run(String[] argv, ClassLoader classLoader) throws IOException {
        RuntimeOptions runtimeOptions = new RuntimeOptions(new ArrayList<String>(asList(argv)));

        ResourceLoader resourceLoader = new CustomMultiLoader(classLoader);
        ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
        Runtime runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
        runtime.run();
        runtime.printSummary();
        return runtime.exitStatus();
    }
}
