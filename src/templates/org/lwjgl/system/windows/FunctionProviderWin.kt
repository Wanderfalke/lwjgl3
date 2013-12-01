/* 
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.system.windows

import java.io.PrintWriter
import org.lwjgl.generator.*

/** Dummy FunctionProvider for dynamically loaded Windows functions. Nothing to do here, we use Functions instances directly. */
public val FunctionProviderWin: FunctionProvider = object : FunctionProvider(WINDOWS_PACKAGE, "*DUMMY*") {

	override val hasCurrentCapabilities: Boolean = false

	override fun PrintWriter.generateFunctionGetters(nativeClass: NativeClass): Unit {
	}
	override fun PrintWriter.generateContent(): Unit {
	}

}

// DSL Extensions

public fun String.nativeClassWin(init: (NativeClass.() -> Unit)? = null): NativeClass =
	nativeClass(WINDOWS_PACKAGE, this, functionProvider = FunctionProviderWin, init = init)

val DLL_WARNING =
	"""Features must be detected on a function-by-function basis. A function pointer will have a $NULL (0L) value when the corresponding
		function is not supported in the Windows version we're running."""

val DLL_FUNC = Capabilities(expression = FUNCTION_ADDRESS, override = true)
val DLL_ADDRESS = mods(
	virtual,
	nullable // the generator emits checkFunctionAddress, no need to check again
) _ voidptr.IN(FUNCTION_ADDRESS, "the DLL function address")