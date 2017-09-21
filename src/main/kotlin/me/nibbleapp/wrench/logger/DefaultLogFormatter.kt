package me.nibbleapp.wrench.logger

class DefaultLogFormatter : LogFormatter {

  override val DEFAULT_STACK_LENGTH: Int = 3

  private val TOP_LEFT_CORNER = '┌'
  private val BOTTOM_LEFT_CORNER = '└'
  private val MIDDLE_CORNER = '├'
  private val HORIZONTAL_LINE = '│'
  private val DOUBLE_DIVIDER = "─────────────────────────────────────────────"
  private val SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
  private val TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
  private val BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
  private val EMPTY_LINE = "\n"
  private val MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER + EMPTY_LINE

  override fun format(message: String, throwable: Throwable?, depth: Int): String {
    val trace = getTrace(throwable)
    val initialStackPosition = getInitialStackPosition(trace, throwable)
    val intRange = initRange(throwable, initialStackPosition, depth)

    val formattedStack = formatStackTrace(intRange, trace, initialStackPosition)
    val formattedError = formatError(throwable)
    val formattedThread = formatThread(Thread.currentThread().name)

    return formatMessage(formattedError, formattedThread, formattedStack, message)
  }

  private fun formatThread(name: String): String = " Thread: $name"


  private fun formatMessage(formattedError: String, formattedThread: String, formattedStack: String, message: String): String {
    return "$TOP_BORDER$EMPTY_LINE" +
           "$HORIZONTAL_LINE$formattedThread$EMPTY_LINE" +
           "$MIDDLE_BORDER$formattedError$formattedStack$EMPTY_LINE" +
           "$MIDDLE_BORDER$HORIZONTAL_LINE $message$EMPTY_LINE" +
           BOTTOM_BORDER
  }

  private fun getTrace(throwable: Throwable?) = throwable?.stackTrace ?: Thread.currentThread().stackTrace

  private fun formatError(throwable: Throwable?): String {
    return if (throwable != null) {
      "$HORIZONTAL_LINE $throwable$EMPTY_LINE$MIDDLE_BORDER"
    } else {
      String()
    }
  }

  private fun formatStackTrace(intRange: IntRange, trace: Array<StackTraceElement>,
                               initialStackPosition: Int): String {
    return intRange
        .map { position -> formatStackTraceElement(trace[position], (position - initialStackPosition)) }
        .fold(String()) { full, item -> full.plus(item) }
        .removeSuffix(EMPTY_LINE)
  }

  private fun initRange(throwable: Throwable?, initialStackPosition: Int, depth: Int): IntRange {
    return if (null == throwable) {
      (initialStackPosition..initialStackPosition + depth)
    } else {
      0..depth
    }
  }

  private fun getInitialStackPosition(stackTraceElement: Array<StackTraceElement>, throwable: Throwable?): Int =
      getLoggerIndex(throwable?.stackTrace ?: stackTraceElement)

  private fun getLoggerIndex(stackTraceElement: Array<StackTraceElement>): Int {
    val element = stackTraceElement.first { it.methodName == "d" }
    return stackTraceElement.indexOf(element) + 2
  }

  private fun formatStackTraceElement(stackTraceElement: StackTraceElement, level: Int): String {
    val start = (0..level).fold(String()) { total, _ -> total.plus("  ") }
    val className = "$start${stackTraceElement.className.substringAfterLast(".")}"
    val methodName = stackTraceElement.methodName
    val fileName = stackTraceElement.fileName
    val lineNumber = stackTraceElement.lineNumber
    return formatStackTraceElement(className, methodName, fileName, lineNumber)
  }

  private fun formatStackTraceElement(className: String, methodName: String?, fileName: String?,
                                      lineNumber: Int): String {
    return "$HORIZONTAL_LINE " +
           "$className.$methodName " +
           "($fileName:$lineNumber)$EMPTY_LINE"
  }
}
