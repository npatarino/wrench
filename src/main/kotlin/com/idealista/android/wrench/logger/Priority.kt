package com.idealista.android.wrench.logger

sealed class Priority(val value: Int) {

  class VERBOSE : Priority(2)
  class DEBUG : Priority(3)
  class INFO : Priority(4)
  class WARN : Priority(5)
  class ERROR : Priority(6)
  class ASSERT : Priority(7)

}