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
 * Problem 36
 *
 *  The decimal number, 585 = 1001001001_(2) (binary), is palindromic
 *  in both bases.
 *
 *  Find the sum of all numbers, less than one million, which are palindromic
 *  in base 10 and base 2.
 *
 *  (Please note that the palindromic number, in either base, may not
 *  include leading zeros.)
 *
 * @author : Todd Cook
 * @since : 4/24/2011
 */

object problem_36 {

  // from prob 4
  def isPalindrome (s: String): Boolean = s.reverse.mkString == s

  def answer = {
    val palindromes = for {x <- (1 to 1000000)
                           if (isPalindrome(x.toString)) &&
                             isPalindrome(java.lang.Integer.toBinaryString(x))
    } yield {
      (x)
    }
    println(palindromes.toList)
    palindromes.sum
  }

  def main (args: Array[String]) = {
    println(answer)
  }
}