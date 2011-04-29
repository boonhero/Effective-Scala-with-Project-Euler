/*
 * Copyright (c) 2011, Todd Cook.
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification,
 *  are permitted provided that the following conditions are met:
 *
 *      * Redistributions of source code must retain the above copyright notice,
 *        this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright notice,
 *        this list of conditions and the following disclaimer in the documentation
 *        and/or other materials provided with the distribution.
 *      * Neither the name of the <ORGANIZATION> nor the names of its contributors
 *        may be used to endorse or promote products derived from this software
 *        without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *  DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *  CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.cookconsulting.projecteuler

/**
 *
Problem 2
Each new term in the Fibonacci sequence is generated by adding
the previous two terms. By starting with 1 and 2, the first 10 terms will be:
1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
Find the sum of all the even-valued terms in the sequence which do not exceed
four million.
 *
 * @author : Todd Cook
 * @since : 4/24/2011
 */

object problem_2 {


    def makeFibo (ceiling: Int, col: List[Int]): List[Int] = {
        if (col == Nil)
            return makeFibo (ceiling, List (0, 1, 1))
        if (col.last >= ceiling)
            return col

        var total = col ((col.size) - 1) + col ((col.size) - 2)
        val newCollection = total :: (col reverse) reverse

        if (newCollection.last == ceiling)
            return newCollection
        else
            makeFibo (ceiling, newCollection)
    }


    def maxFibo (ceiling: Int): List[Int] = makeFibo (ceiling, Nil)

    def answer = maxFibo (4000000).filter (_ % 2 == 0).foldLeft (0) (_ + _)

    /**
     *
    This manually defines the first two terms of the Fibonacci sequence, then
    recursively defines an infinite stream of the remaining Fibonacci terms.
    fib is the Fibonacci sequence starting at zero (0, 1, 1, 2, 3, ...).
    fib.tail is the Fibonacci sequence starting at one (1, 1, 2, 3, 5, ...).
    fib.zip(fib.tail) is the two sequences zipped into a sequence of
    pairs ((0, 1), (1, 1), (1, 2), (2, 3), ...).
    We then use map to sum the two parts of each pair (._1 and ._2) and
    complete the recursive definition of the rest of fib,
    after the first two terms (1, 2, 3, 5, ...).
    Thanks to Stream, the terms of the Fibonacci sequence are only evaluated
    as they are needed, so we can represent an infinite stream without
    incurring infinite computation.
    We can verify that we computed the Fibonacci numbers correctly by inspecting
    the first few terms of our Stream with take and print.
    scala> fib.take(15).print
    0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, Stream.empty

    That looks about right. Now lets filter so we only have the even-valued
    Fibonacci terms, use takeWhile to grab the terms below a million, and add
    them up. Since I'm getting tired of foldLeft, let's Pimp my Library and
    add a "sum" method to our Fibonacci sequence (indeed, to any Iterable[Int]).

    res8: Int = 1089154

     */

    def answer2 = {
        lazy val fib: Stream[Int] = Stream.cons (0,
            Stream.cons (1, fib.zip (fib.tail).map (p => p._1 + p._2)))
        implicit def iterableWithSum (it: Iterable[Int]) = new {
            def sum = it.foldLeft (0) (_ + _)
        }
        fib.filter (_ % 2 == 0).takeWhile (_ <= 4000000).sum
    }

    def fibInc (pair: Tuple2[Int, Int]): Tuple2[Int, Int] = {
        (pair._2, (pair._1 + pair._2))
    }

    def answer3 = {
        var total = 0
        var seed = (1, 2)
        while (seed._2 < 4000000) {
            if (seed._2 % 2 == 0) {
                total = total + seed._2
            }
            seed = fibInc (seed)
        }
        total
    }

    def main (args: Array[String]) = {
        println (answer)
        println (answer2)
        println (answer3)
    }
}