/*
 * Copyright 2015-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.console;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.MAINTAINED;

import java.io.PrintWriter;

import org.apiguardian.api.API;
import org.junit.platform.console.options.CommandLineOptionsParser;
import org.junit.platform.console.options.CommandResult;
import org.junit.platform.console.options.MainCommand;

/**
 * The {@code ConsoleLauncher} is a stand-alone application for launching the
 * JUnit Platform from the console.
 *
 * @since 1.0
 */
@API(status = MAINTAINED, since = "1.0")
public class ConsoleLauncher {

	public static void main(String... args) {
		PrintWriter out = new PrintWriter(System.out);
		PrintWriter err = new PrintWriter(System.err);
		CommandResult<?> result = run(out, err, args);
		System.exit(result.getExitCode());
	}

	@API(status = INTERNAL, since = "1.0")
	public static CommandResult<?> run(PrintWriter out, PrintWriter err, String... args) {
		ConsoleLauncher consoleLauncher = new ConsoleLauncher(null, out, err);
		return consoleLauncher.run(args);
	}

	private final PrintWriter out;
	private final PrintWriter err;

	ConsoleLauncher(CommandLineOptionsParser commandLineOptionsParser, PrintWriter out, PrintWriter err) {
		this.out = out;
		this.err = err;
	}

	CommandResult<?> run(String... args) {
		try {
			return MainCommand.run(out, err, args);
		}
		finally {
			out.flush();
			err.flush();
		}
	}

}
