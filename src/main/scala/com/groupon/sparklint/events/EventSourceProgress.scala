/*
 Copyright 2016 Groupon, Inc.
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package com.groupon.sparklint.events

/**
  * A simple case class that provides information about the pointer progress of a specific EventSourceLike
  * implementation.
  *
  * @author swhitear 
  * @since 9/12/16.
  */
@throws[IllegalArgumentException]
case class EventSourceProgress(count: Int, position: Int) {
  require(count >= 0)
  require(position >= 0)
  require(position <= count)

  private val safeCount: Double = if (count == 0) 1 else count

  val atStart = position == 0

  val atEnd = position == count

  val hasNext = !atEnd

  val hasPrevious = !atStart

  val percent = ((position / safeCount) * 100).round

  val description = s"$position / $count ($percent%)"

  override def toString: String = s"$position of $count"
}