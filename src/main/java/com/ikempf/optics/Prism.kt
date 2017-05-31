package com.ikempf.optics

import org.funktionale.option.Option

class Prism<A, B>(val getOptional: (A) -> Option<B>, val reverseGet: (B) -> A)