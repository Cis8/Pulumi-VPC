package com.cisotto.myvpc.functor

import com.pulumi.core.Output
import scala.jdk.FunctionConverters._

given Functor[Output] with
  extension [A](oa: Output[A])
    def map[B](f: A => B): Output[B] =
      oa.applyValue(f.asJava)
      // up here we need to convert the scala function to a java function with a library (import scala.jdk.FunctionConverters._) using f.asJAva


