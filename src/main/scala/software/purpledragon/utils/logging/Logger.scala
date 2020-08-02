/*
 * Copyright 2020 Michael Stringer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package software.purpledragon.utils.logging

import org.slf4j.{LoggerFactory, Logger => Slf4jLogger}

/**
 * Scala wrapper around an SLF4J `Logger`. This smooths over some of the coercion issues when using Scala [[AnyVal]]
 * types with SLF4J.
 *
 * @param name Name of the logger instance.
 */
class Logger(name: String) {
  private val logger: Slf4jLogger = LoggerFactory.getLogger(name)

  def isTraceEnabled: Boolean = logger.isTraceEnabled
  def trace(msg: String): Unit = logger.trace(msg)
  def trace(msg: String, params: Any*): Unit = logger.trace(msg, coerceParams(params): _*)
  def trace(msg: String, t: Throwable, params: Any*): Unit = logger.trace(msg, coerceParams(params :+ t): _*)

  def isDebugEnabled: Boolean = logger.isDebugEnabled
  def debug(msg: String): Unit = logger.debug(msg)
  def debug(msg: String, params: Any*): Unit = logger.debug(msg, coerceParams(params): _*)
  def debug(msg: String, t: Throwable, params: Any*): Unit = logger.debug(msg, coerceParams(params :+ t): _*)

  def isInfoEnabled: Boolean = logger.isInfoEnabled
  def info(msg: String): Unit = logger.info(msg)
  def info(msg: String, params: Any*): Unit = logger.info(msg, coerceParams(params): _*)
  def info(msg: String, t: Throwable, params: Any*): Unit = logger.info(msg, coerceParams(params :+ t): _*)

  def isWarnEnabled: Boolean = logger.isWarnEnabled
  def warn(msg: String): Unit = logger.warn(msg)
  def warn(msg: String, params: Any*): Unit = logger.warn(msg, coerceParams(params): _*)
  def warn(msg: String, t: Throwable, params: Any*): Unit = logger.warn(msg, coerceParams(params :+ t): _*)

  def isErrorEnabled: Boolean = logger.isErrorEnabled
  def error(msg: String): Unit = logger.error(msg)
  def error(msg: String, params: Any*): Unit = logger.error(msg, coerceParams(params): _*)
  def error(msg: String, t: Throwable, params: Any*): Unit = logger.error(msg, coerceParams(params :+ t): _*)

  private def coerceParams(params: Seq[Any]): Seq[AnyRef] =
    params map { p =>
      p.asInstanceOf[AnyRef]
    }
}
