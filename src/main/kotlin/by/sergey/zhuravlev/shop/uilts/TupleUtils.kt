package by.sergey.zhuravlev.shop.uilts

import by.sergey.zhuravlev.tuples.Tuple2
import by.sergey.zhuravlev.tuples.Tuples

inline fun <T, T2> T.with(block: (T) -> T2): Tuple2<T, T2> {
  return Tuples.of(this, block(this))
}

inline fun <T, T2> T.withNullable(block: (T) -> T2?): Tuple2<T, T2?> {
  return Tuples.of(this, block(this))
}

operator fun <T1, T2> Tuple2<T1, T2>.component1(): T1 {
  return t1
}

operator fun <T1, T2> Tuple2<T1, T2>.component2(): T2 {
  return t2
}
