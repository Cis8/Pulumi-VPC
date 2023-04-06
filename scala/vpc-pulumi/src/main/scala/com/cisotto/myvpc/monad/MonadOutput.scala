package com.cisotto.myvpc.monad

import com.pulumi.core.Output
import scala.jdk.FunctionConverters._

given Monad[Output] with
  def pure[A](x: A): Output[A] = Output.of(x)
  extension [A](oa: Output[A])
    def flatMap[B](f: A => Output[B]): Output[B] = oa.apply(f.asJava)
    //override def map[B](f: A => B): Output[B] = oa.flatMap(f.andThen(pure))
    //override def map[B](f: A => B): Output[B] = oa.applyValue(f.asJava)
