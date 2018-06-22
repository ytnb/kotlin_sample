import kotlin.coroutines.experimental.buildIterator

fun main(args: Array<String>) {

    /*
        Kotlin Collection
     */

    /*
        Properties
     */
    //val <T> any_array<T>.indices: IntRange
    //  配列の有効なインデックスの範囲を返します。
    //val Collection<*>.indices: IntRange
    //  このコレクションの有効なインデックスのIntRangeを返します。
    fun example1() {
        val list = listOf("a", "b", "c")
        println(list.indices) // 0..2
        for (i in list.indices) {
            println(list[i])
        }
    }

    //val <T> any_array<T>.lastIndex: Int
    //  配列の最後の有効なインデックスを返します。
    //val <T> List<T>.lastIndex: Int
    //  リスト内の最後の項目のインデックスを返します。リストが空の場合は-1を返します。
    fun example2() {
        val list = listOf("a", "b", "c")
        println(list.lastIndex) // 2
        println(list[list.lastIndex]) // c
    }

    /*
        Functions
     */

    //fun <T> Iterable(iterator: () -> Iterator<T>): Iterable<T>
    //  反復子関数が与えられた場合、その関数によって提供される反復子を介して値を返す反復可能なインスタンスが構築されます。
    fun sample3() {
        val iterable = Iterable {
            buildIterator {
                yield(42)
                yieldAll(1..5 step 2)
            }
        }
        val result = iterable.mapIndexed { index, value -> "$index: $value" }
        println(result) // [0: 42, 1: 1, 2: 3, 3: 5]
        repeat(1) {
            val sum = iterable.sum()
            println(sum) // 51
        }
    }

    //fun <T> List(size: Int, init: (index: Int) -> T): List<T>
    //  指定されたサイズを持つ新しい読み取り専用リストを作成します。
    //  各要素は、指定されたinit関数を呼び出すことによって計算されます。
    //  init関数は、そのインデックスが与えられたリスト要素を返します。
    fun sample4() {
        val squares = List(5) { (it + 1) * (it + 1) }
        println(squares) // [1, 4, 9, 16, 25]
    }

    //fun <T> MutableList(size: Int, init: (index: Int) -> T): MutableList<T>
    //  Listと同様
    fun sample5() {
        val list = MutableList(3) { index -> 'A' + index }
        println(list) // [A, B, C]
        list.clear()
        println(list) // []
    }

    //fun <T> MutableCollection<in T>.addAll(elements: Iterable<T>): Boolean
    //fun <T> MutableCollection<in T>.addAll(elements: Sequence<T>): Boolean
    //fun <T> MutableCollection<in T>.addAll(elements: Array<out T>): Boolean
    //  指定された要素コレクション/シーケンス/配列のすべての要素をこのMutableCollectionに追加します。
    fun sample6() {
        val list = mutableListOf("a", "b", "c")
        val addlist = listOf("x", "y", "z")
        list.addAll(addlist)
        println(list) // [a, b, c, x, y, z]
    }

    // todo
    //fun <T, K, R> Grouping<T, K>.aggregate(
    // operation: (key: K, accumulator: R?, element: T, first: Boolean) -> R): Map<K, R>
    //  キーでグループ化元の要素をグループ化し、以前に累積された値と現在の要素を引数として渡しながら、
    //  各グループの要素に順番に操作を適用し、その結果を新しいマップに保存します。

    // todo
    //fun <T, K, R, M : MutableMap<in K, R>> Grouping<T, K>.aggregateTo(destination: M,
    // operation: (key: K, accumulator: R?, element: T, first: Boolean) -> R): M
    //  グループ化元の要素をキーでグループ化し、各グループの要素に順次累積された値と現在の要素を引数として渡して、
    //  指定された宛先マップに結果を格納します。

    //fun <T> Array<out T>.all(predicate: (T) -> Boolean): Boolean
    //fun ***Array.all(predicate: (***) -> Boolean): Boolean
    //fun <T> Iterable<T>.all(predicate: (T) -> Boolean): Boolean
    //fun <K, V> Map<out K, V>.all(predicate: (Entry<K, V>) -> Boolean): Boolean
    //  すべての要素/エントリが指定された述語と一致する場合はtrueを返します。
    fun sample7() {
        val isEven: (Int) -> Boolean = { it % 2 == 0 }
        val zeroToTen = 0..10
        println("zeroToTen.all {isEven(it)} is ${zeroToTen.all { isEven(it) }}") // false
        println("zeroToTen.all(isEven) is ${zeroToTen.all(isEven)}") // false
        val evens = zeroToTen.map { it * 2 }
        println("evens.all {isEven(it) } is ${evens.all { isEven(it) }}") // true
        val emptyList = emptyList<Int>()
        println("emptyList.all { false } is ${emptyList.all { false }}") // true
    }

    //fun <T> any_array<T>.any(): Boolean
    //fun <T> Iterable<T>.any(): Boolean
    //fun <K, V> Map<out K, V>.any(): Boolean
    //  配列に少なくとも1つの要素がある場合はtrueを返します。
    //fun <T> Array<out T>.any(predicate: (T) -> Boolean): Boolean
    //fun ***Array.any(predicate: (***) -> Boolean): Boolean
    //fun <K, V> Map<out K, V>.any(predicate: (Entry<K, V>) -> Boolean): Boolean
    //  少なくとも1つの要素が指定された述語と一致する場合はtrueを返します。
    fun sample8() {
        val emptyList = emptyList<Int>()
        println(emptyList.any()) // false
        val nonEmptyList = listOf(1, 2, 3)
        println(nonEmptyList.any()) // true

        val isEven: (Int) -> (Boolean) = { it % 2 == 0 }
        val zeroToTen = 0..10
        println(zeroToTen.any { isEven(it) }) // true
        println(zeroToTen.any(isEven)) // true
        val odds = zeroToTen.map { it * 2 + 1 }
        println(odds.any { isEven(it) }) // false
        val emptyFloat = emptyList<Float>()
        println(emptyFloat.any { true }) // false
    }

    //fun <T> arrayListOf(): ArrayList<T>
    //  空の新しいArrayListを返します。
    //fun <T> arrayListOf(vararg elements: T): ArrayList<T>
    //  指定された要素を持つ新しいArrayListを返します。
    fun sample9() {
        val list = arrayListOf<Int>()
        println(list.isEmpty()) // true
        list.addAll(listOf(1, 2, 3))
        println(list) // [1, 2, 3]

        val lists = arrayListOf(1, 2, 3)
        println(lists) // [1, 2, 3]
        lists += listOf(4, 5)
        println(lists) // [1, 2, 3, 4, 5]
    }

    // todo
    //fun <T> any_array<T>.asIterable(): Iterable<T>
    //  反復処理中に元の配列をラップして要素を返す反復可能なインスタンスを作成します。
    //fun <T> Iterable<T>.asIterable(): Iterable<T>
    //  このコレクションをIterableとして返します。
    //fun <K, V> Map<out K, V>.asIterable(): Iterable<Entry<K, V>>
    //  反復処理中に元のマップをラップしてそのエントリを返すIterableインスタンスを作成します。

    //actual fun <T> any_array<T>.asList(): List<T>
    //  元の配列をラップするリストを返します。
    fun sample10() {
        val array = intArrayOf(0, 0, 0)
        println(array.asList()) // [0, 0, 0]
    }

    //fun <T> List<T>.asReversed(): List<T>
    //  元のListの逆読み込みビューを返します。 元のリストに加えられたすべての変更は、元のリストに反映されます。
    //fun <T> MutableList<T>.asReversed(): MutableList<T>
    //  元の変更可能なリストの、変更されたビューを返します。
    //  元のリストに加えられたすべての変更は、逆のものに反映され、逆も同様です。
    fun sample11() {
        val original = mutableListOf('a', 'b', 'c', 'd', 'e')
        val originalReadOnly = original as List<Char>
        val reversed = originalReadOnly.asReversed()
        println(original) // [a, b, c, d, e]
        println(reversed) // [e, d, c, b, a]

        original.add('f')
        println(original) // [a, b, c, d, e, f]
        println(reversed) // [f, e, d, c, b, a]

        original[original.lastIndex] = 'z'
        println(original) // [a, b, c, d, e, z]
        println(reversed) // [z, e, d, c, b, a]
    }

    //fun <T> any_array<T>.asSequence(): Sequence<T>
    //fun <T> Iterable<T>.asSequence(): Sequence<T>
    //fun <K, V> Map<out K, V>.asSequence(): Sequence<Entry<K, V>>
    //  イテレーション時に元の配列をラップするSequenceインスタンスを作成します。
    fun sample12() {
        val array = arrayOf('a', 'b', 'c')
        val sequence = array.asSequence()
        println(sequence.joinToString()) // a, b, c
    }

    // todo
    //fun <T, K, V> any_array<T>.associate(transform: (T) -> Pair<K, V>): Map<K, V>
    //fun <T, K, V> Iterable<T>.associate(transform: (T) -> Pair<K, V>): Map<K, V>
    //  指定された配列の要素に適用される変換関数によって提供されるキーと値のペアを含むマップを返します。

    // todo
    //fun <T, K> any_array<T>.associateBy(keySelector: (T) -> K): Map<K, T>
    //fun <T, K> Iterable<T>.associateBy(keySelector: (T) -> K): Map<K, T>
    //  各要素に適用されたkeySelector関数から返されたキーによってインデックスされた、指定された配列の要素を含むMapを返します。
    //fun <T, K, V> any_array<T>.associateBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V>
    //fun <T, K, V> Iterable<T>.associateBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V>
    //  valueTransformによって提供され、指定された配列の要素に適用される
    //  keySelector関数によってインデックスされた値を含むMapを返します。

    // todo
    //fun <T, K, M : MutableMap<in K, in T>> any_array<T>.associateByTo(destination: M, keySelector: (T) -> K): M
    //fun <T, K, M : MutableMap<in K, in T>> Iterable<T>.associateByTo(destination: M, keySelector: (T) -> K): M
    //  指定された配列の各要素に適用されるkeySelector関数によってkeyが提供され、
    //  valueが要素そのものである、キーと値のペアを持つ宛先可変マップを生成して返します。
    //fun <T, K, V, M : MutableMap<in K, in V>> any_array<T>.associateByTo(destination: M, keySelector: (T) -> K,
    //    valueTransform: (T) -> V): M
    //fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associateByTo(destination: M, keySelector: (T) -> K,
    //    valueTransform: (T) -> V): M
    //  キーと値のペア（キーがkeySelector関数によって提供され、valueが指定された配列の要素に適用される
    //  valueTransform関数によって提供される）を使用して、宛先変更可能マップを生成して返します。

    // todo
    //fun <T, K, V, M : MutableMap<in K, in V>> any_array<T>.associateTo(destination: M, transform: (T) -> Pair<K, V>): M
    //fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associateTo(destination: M, transform: (T) -> Pair<K, V>): M
    //  指定された配列の各要素に適用される変換関数によって提供されるキーと値のペアを使用して、変更可能なマップを生成して返します。

    //fun Array<out ***>.average(): Double
    //fun ***Array.average(): Double
    //fun Iterable<***>.average(): Double
    //  配列内の要素の平均値を返します。
    fun sample13() {
        val avg = List(5, { it * 2 })
        println(avg) // [0, 2, 4, 6, 8]
        println(avg.average()) // 4.0
    }

    //fun <T> Array<out T>.binarySearch(element: T, comparator: Comparator<in T>,
    //    fromIndex: Int = 0, toIndex: Int = size): Int
    //  バイナリ検索アルゴリズムを使用して、指定された要素の配列または配列の範囲を検索します。
    //  配列は、指定されたコンパレータに従ってソートされると予想されます。そうでない場合、結果は未定義です。
    //fun <T> any_array<T>.binarySearch(element: T, fromIndex: Int = 0, toIndex: Int = size): Int
    //  バイナリ検索アルゴリズムを使用して、指定された要素の配列または配列の範囲を検索します。
    //  配列はソートされると予想され、そうでない場合は結果が未定義です。
    //fun <T : Comparable<T>> List<T?>.binarySearch(element: T?, fromIndex: Int = 0, toIndex: Int = size): Int
    //  バイナリ検索アルゴリズムを使用して、指定された要素のリストまたはその範囲を検索します。
    //  リストは、要素のComparable自然順序付けに従って昇順にソートされることが期待されます。そうでない場合、結果は未定義です。
    //fun <T> List<T>.binarySearch(element: T, comparator: Comparator<in T>, fromIndex: Int = 0, toIndex: Int = size): Int
    //  バイナリ検索アルゴリズムを使用して、指定された要素のリストまたはその範囲を検索します。
    //  リストは、指定された比較器に従って昇順にソートされることが期待されます。そうでない場合、結果は未定義です。
    //fun <T> List<T>.binarySearch(fromIndex: Int = 0, toIndex: Int = size, comparison: (T) -> Int): Int
    //  バイナリ検索アルゴリズムを使用して比較関数がゼロを返す要素のリストまたはその範囲を検索します。
    //  リストは、提供された比較に従って昇順にソートされることが期待されます。そうでない場合、結果は未定義です。
    fun sample14() {
        val list = mutableListOf('a', 'b', 'c', 'd', 'e')
        println(list.binarySearch('d')) // 3
        list.remove('d')
        val invertedInsertionPoint = list.binarySearch('d')
        println(invertedInsertionPoint) // -4
        val actualInsertionPoint = -(invertedInsertionPoint + 1)
        println(actualInsertionPoint) // 3
        list.add(actualInsertionPoint, 'd')
        println(list) // [a, b, c, d, e]
    }

    //fun <T, K : Comparable<K>> List<T>.binarySearchBy(key: K?, fromIndex: Int = 0,
    //    toIndex: Int = size, selector: (T) -> K?): Int
    //  バイナリ検索アルゴリズムを使用して、指定されたセレクタ関数によって返されたキーを持つ要素が、
    //  指定されたキー値と等しい場合、このリストまたはその範囲を検索します。
    //  このリストは、要素のキーのComparable Natural Orderingに従って昇順にソートされることが期待されます。
    //  それ以外の場合は結果は未定義です。
    fun sample15() {
        data class Box(val value: Int)

        val number = listOf(1, 3, 7, 10, 12)
        val boxs = number.map { Box(it) }
        println(boxs) // [Box(value=1), Box(value=3), Box(value=7), Box(value=10), Box(value=12)]
        println(boxs.binarySearchBy(10) { it.value }) // 3
        println(boxs.binarySearchBy(12) { it.value }) // 4
    }

    //fun <T> Iterable<T>.chunked(size: Int): List<List<T>>
    //  このコレクションを、指定されたサイズを超えないリストのリストに分割します。
    //fun <T, R> Iterable<T>.chunked(size: Int, transform: (List<T>) -> R): List<R>
    //  このコレクションを、指定されたサイズを超えない複数のリストに分割し、与えられた変換関数をそれぞれに適用します。
    fun sample16() {
        val words = "one twu three four five six seven eight nine ten".split(' ')
        val chunks = words.chunked(3)
        println(chunks) // [[one, twu, three], [four, five, six], [seven, eight, nine], [ten]]

        val codonTable = mapOf(
                "ATT" to "Isoleucine",
                "CAA" to "Glutamine",
                "CGC" to "Arginine",
                "GGC" to "Glycine"
        )
        val dnaFragment = "ATTCGCGGCCGCCAA"
        val proteins = dnaFragment.chunked(3) { codon: CharSequence ->
            codonTable[codon.toString()] ?: error("Unknown codon")
        }
        println(proteins) // [Isoleucine, Arginine, Glycine, Arginine, Glutamine]
    }

    //operator fun <T> Array<out T>.component1(): T
    //operator fun ***Array.component1(): ***
    //  コレクションから1番目の要素を返します。
    //operator fun <K, V> Entry<K, V>.component1(): K
    //  マップエントリのキーコンポーネントを返します。
    //operator fun <T> Array<out T>.component2(): T
    //operator fun <T> Array<out T>.component3(): T
    //operator fun <T> Array<out T>.component4(): T
    //operator fun <T> Array<out T>.component5(): T
    fun sample17() {
        val codonTable = mapOf(
                "ATT" to "Isoleucine",
                "CAA" to "Glutamine",
                "CGC" to "Arginine",
                "GGC" to "Glycine"
        )
        //destructuring declarations
        for ((key, value) in codonTable) {
            println("key=$key : value=$value")
        }
    }

    //operator fun <T> any_array<T>.contains(element: T): Boolean
    //  要素が配列内にある場合はtrueを返します。
    //operator fun <T> Iterable<T>.contains(element: T): Boolean
    //  要素がコレクション内にある場合はtrueを返します。
    //operator fun <K, V> Map<out K, V>.contains(key: K): Boolean
    //  mapに指定されたキーが含まれているかどうかを確認します。
    fun sample18() {
        val list = listOf('a', 'b', 'c', 'd', 'e')
        println(list.contains('d')) // true
        println(list.contains('z')) // false
    }

    //fun <T> Collection<T>.containsAll(elements: Collection<T>): Boolean
    //指定されたコレクションのすべての要素がこのコレクションに含まれているかどうかを判定します。
    fun sample19() {
        val collection = mutableListOf('a', 'b')
        val test = listOf('a', 'b', 'c')
        println(collection.containsAll(test)) // false
        collection.add('c')
        println(collection.containsAll(test)) // true
    }

    // todo
    //fun <K> Map<out K, *>.containsKey(key: K): Boolean
    //  マップに指定されたキーが含まれている場合はtrueを返します。
    //    K型のキーを渡す必要があるcontainsKeyの型安全性の制限を克服することを可能にします。

    // todo
    //fun <K, V> Map<K, V>.containsValue(value: V): Boolean
    //  マップが1つまたは複数のキーを指定された値にマップする場合はtrueを返します。
    //   V型の値を渡す必要があるcontainsValueの型安全性の制限を克服することを可能にします。

    // todo
    //infix actual fun <T> Array<out T>.contentDeepEquals(other: Array<out T>): Boolean
    //  指定された2つの配列が互いに深く等しい場合、つまり同じ要素の同じ番号を同じ順序で含む場合はtrueを返します。

    // todo
    //actual fun <T> Array<out T>.contentDeepHashCode(): Int
    //  この配列の内容に基づいて、Listであるかのようにハッシュコードを返します。 ネストされた配列はリストとしても扱われます。

    //actual fun <T> Array<out T>.contentDeepToString(): String
    //  この配列の内容の文字列表現をListのように返します。 ネストされた配列はリストとしても扱われます。
    fun sample20() {
        val matrix = arrayOf(
                intArrayOf(3, 7, 9),
                intArrayOf(0, 1, 0),
                intArrayOf(2, 4, 8)
        )
        println(matrix.contentDeepToString()) // [[3, 7, 9], [0, 1, 0], [2, 4, 8]]
    }

    // todo
    //infix actual fun <T> any_array<T>.contentEquals(other: Array<out T>): Boolean
    //  指定された2つの配列が構造的に互いに等しい場合、つまり同じ要素の同じ番号を同じ順序で含む場合はtrueを返します。

    // todo
    //actual fun <T> any_array<T>.contentHashCode(): Int
    //  この配列の内容に基づいて、Listであるかのようにハッシュコードを返します。

    //actual fun <T> any_array<T>.contentToString(): String
    //  Listであるかのように、指定された配列の内容の文字列表現を返します。
    fun sample21() {
        val array = arrayOf("apples", "oragnges", "lime")
        println(array.contentToString()) // [apples, oragnges, lime]
    }

    //actual fun <T> any_array<T>.copyOf(): Array<T>
    //  元の配列のコピーである新しい配列を返します。
    //actual fun ByteArray.copyOf(newSize: Int): ByteArray
    //  指定されたnewSizeにサイズ変更された元の配列のコピーである新しい配列を返します。
    //  必要であれば、コピーは切り捨てられ、最後に0の値が埋め込まれます。
    //actual fun BooleanArray.copyOf(newSize: Int): BooleanArray
    //  指定されたnewSizeにサイズ変更された元の配列のコピーである新しい配列を返します。
    //  コピーは、必要に応じて最後に切り捨てられたり、'false'が埋め込まれたりします。
    //actual fun CharArray.copyOf(newSize: Int): CharArray
    //  指定されたnewSizeにサイズ変更された元の配列のコピーである新しい配列を返します。
    //  コピーは、必要に応じて末尾がヌル文字（\ u0000）の値で切り捨てられるかパディングされます。
    //actual fun <T> Array<T>.copyOf(newSize: Int): Array<T?>
    //  指定されたnewSizeにサイズ変更された元の配列のコピーである新しい配列を返します。
    //  コピーは、必要に応じて最後に切り捨てられ、または'null'で埋められます。
    fun sample22() {
        val array = arrayOf("apples", "oranges", "limes")
        val arrayCopy = array.copyOf()
        println(arrayCopy.contentToString()) // [apples, oranges, limes]

        val array2 = intArrayOf(1, 2, 3)
        val arrayCopyPadded = array2.copyOf(5)
        println(arrayCopyPadded.contentToString()) // [1, 2, 3, 0, 0]
        val arrayCopyTruncated = array2.copyOf(2)
        println(arrayCopyTruncated.contentToString()) // [1, 2]
    }

    // todo
    //actual fun <T> any_array<T>.copyOfRange(fromIndex: Int, toIndex: Int): Array<T>
    //  元の配列の範囲のコピーである新しい配列を返します。

    // todo
    //fun <T> any_array<T>.count(): Int
    //  この配列内の要素の数を返します。
    //fun <T> Array<out T>.count(predicate: (T) -> Boolean): Int
    //  指定された述語に一致する要素の数を返します。
    //fun <T> Iterable<T>.count(): Int
    //  このコレクション内の要素の数を返します。
    //fun <K, V> Map<out K, V>.count(): Int
    //  このマップのエントリ数を返します。
    // fun <K, V> Map<out K, V>.count(predicate: (Entry<K, V>) -> Boolean): Int
    //  与えられた述語に一致するエントリの数を返します。

    //fun <T> any_array<T>.distinct(): List<T>
    //  指定された配列の重複削除済みリストを返します。
    //fun <T> Iterable<T>.distinct(): List<T>
    //  指定されたコレクションの重複削除済みリストを返します。
    fun sample23() {
        val list = listOf(1, 2, 3, 2, 3)
        println(list.distinct()) // [1, 2, 3]
    }

    // todo
    //fun <T, K> any_array<T>.distinctBy(selector: (T) -> K): List<T>
    //  指定されたセレクタ関数によって返された異なるキーを持つ指定された配列の要素だけを含むリストを返します。
    //fun <T, K> Iterable<T>.distinctBy(selector: (T) -> K): List<T>
    //  指定されたセレクタ関数によって返された異なるキーを持つ、指定されたコレクションの要素のみを含むリストを返します。

    //fun <T> Array<out T>.drop(n: Int): List<T>
    //fun ***Array.drop(n: Int): List<***>
    //fun <T> Iterable<T>.drop(n: Int): List<T>
    //  最初のn個の要素を除くすべての要素を含むリストを返します。
    //fun <T> Array<out T>.dropLast(n: Int): List<T>
    //fun ***Array.dropLast(n: Int): List<***>
    //fun <T> List<T>.dropLast(n: Int): List<T>
    //  最後のn個の要素を除くすべての要素を含むリストを返します。
    //fun <T> Array<out T>.dropLastWhile(predicate: (T) -> Boolean): List<T>
    //fun ***Array.dropLastWhile(predicate: (***) -> Boolean): List<***>
    //fun <T> List<T>.dropLastWhile(predicate: (T) -> Boolean): List<T>
    //  指定された述語を満たす最後の要素を除くすべての要素を含むリストを返します。
    //fun <T> Array<out T>.dropWhile(predicate: (T) -> Boolean): List<T>
    //fun ***Array.dropWhile(predicate: (***) -> Boolean): List<***>
    //fun <T> Iterable<T>.dropWhile(predicate: (T) -> Boolean): List<T>
    //  与えられた述語を満たす最初の要素を除くすべての要素を含むリストを返します。
    fun sample24() {
        val chars = ('a'..'z').toList()
        println(chars.size) // 26
        println(chars.drop(23)) // [x, y, z]
        println(chars.dropLast(23)) // [a, b, c]
        println(chars.dropWhile { it < 'x' }) // [x, y, z]
        println(chars.dropLastWhile { it > 'c' }) // [a, b, c]
    }

    //actual fun <T, K> Grouping<T, K>.eachCount(): Map<K, Int>
    //  グループ化元の要素をキーごとにグループ化し、各グループの要素をカウントします。
    //fun <T, K, M : MutableMap<in K, Int>> Grouping<T, K>.eachCountTo(destination: M): M
    //  グループ化元の要素をキー別にグループ化し、各グループの要素を指定された宛先マップに集計します。
    fun sample25() {
        val words = "one twu three four five six seven eight nine ten".split(' ')
        val frequenciesByFirstChar = words.groupingBy { it.first() }.eachCount()
        println(frequenciesByFirstChar) // {o=1, t=3, f=2, s=2, e=1, n=1}

        val moreWords = "eleven twelve".split(' ')
        val moreFrequencies = moreWords.groupingBy { it.first() }.eachCountTo(frequenciesByFirstChar.toMutableMap())
        println(moreFrequencies) // {o=1, t=4, f=2, s=2, e=2, n=1}
    }

    // todo
    //fun <T> any_array<T>.elementAt(index: Int): T
    //fun <T> Iterable<T>.elementAt(index: Int): T
    //fun <T> List<T>.elementAt(index: Int): T
    //  指定されたインデックスにある要素を返します。または、
    //  インデックスがこの配列の境界外にある場合はIndexOutOfBoundsExceptionをスローします。

    // todo
    //fun <T> any_array<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T
    //fun <T> Iterable<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T
    //fun <T> List<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T
    //  指定されたインデックスにある要素、またはインデックスがこの配列の境界外にある場合は
    //  defaultValue関数を呼び出した結果を返します。

    // todo
    //fun <T> any_array<T>.elementAtOrNull(index: Int): T?
    //fun <T> Iterable<T>.elementAtOrNull(index: Int): T?
    //fun <T> List<T>.elementAtOrNull(index: Int): T?
    //  指定されたインデックスにある要素を返します。インデックスがこの配列の境界外にある場合はnullを返します。

    //fun <T> emptyList(): List<T>
    //  空の読み取り専用リストを返します。 返されるリストは直列化可能（JVM）です。
    fun sample26() {
        val list = listOf<String>()
        println(list.isEmpty()) // true
        val other: List<Int> = emptyList()
        println("list == other is ${list == other}") // true
        println(list) // []
    }

    //fun <K, V> emptyMap(): Map<K, V>
    //  指定された型の空の読み取り専用マップを返します。
    fun sample27() {
        val map = emptyMap<String, Int>()
        println(map.isEmpty()) // true
        val anotherMap = mapOf<String, Int>()
        println("map == anotherMap is ${map == anotherMap}") // true
    }

    //fun <T> emptySet(): Set<T>
    //  空の読み取り専用セットを返します。 返されるセットは直列化可能（JVM）です。
    fun sample28() {
        val set = setOf<String>()
        println(set.isEmpty()) // true
        val other: Set<Int> = emptySet()
        println("set == other is ${set == other}") // true
        println(set) // []
    }

    // todo
    //fun <T> any_array<T>.fill(element: T, fromIndex: Int = 0, toIndex: Int = size)
    //  元の配列を指定された値で塗りつぶします。
    //actual fun <T> MutableList<T>.fill(value: T)
    //  指定された値でリストを埋めます。

    //fun <T> Array<out T>.filter(predicate: (T) -> Boolean): List<T>
    //fun ***Array.filter(predicate: (***) -> Boolean): List<***>
    //  与えられた述語と一致する要素のみを含むリストを返します。
    //fun <K, V> Map<out K, V>.filter(predicate: (Entry<K, V>) -> Boolean): Map<K, V>
    //  指定された述語に一致するすべてのキーと値のペアを含む新しいマップを返します。
    fun sample29() {
        val originalMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
        val filterdMap = originalMap.filter { it.value < 2 }
        println(filterdMap) // {key1=1}
        println(originalMap) // {key1=1, key2=2, key3=3}
        val nonMatchingPredicate: ((Map.Entry<String, Int>)) -> Boolean = { it.value == 0 }
        val emptyMap = originalMap.filter(nonMatchingPredicate)
        println(emptyMap) // {}
    }

    // todo
    //fun <T> Array<out T>.filterIndexed(predicate: (index: Int, T) -> Boolean): List<T>
    //fun ***Array.filterIndexed(predicate: (index: Int, ***) -> Boolean): List<***>
    //  与えられた述語と一致する要素のみを含むリストを返します。

    // todo
    //fun <T, C : MutableCollection<in T>> Array<out T>.filterIndexedTo(destination: C,
    //    predicate: (index: Int, T) -> Boolean): C
    //fun <C : MutableCollection<in ***>> ***Array.filterIndexedTo(destination: C,
    //    predicate: (index: Int, ***) -> Boolean): C
    //  指定された述語に一致するすべての要素を指定された宛先に追加します。

    // todo
    //fun <R> Array<*>.filterIsInstance(klass: Class<R>): List<R>
    //fun <R> Iterable<*>.filterIsInstance(klass: Class<R>): List<R>
    //  指定されたクラスのインスタンスであるすべての要素を含むリストを返します。
    //fun <R> Array<*>.filterIsInstance(): List<R>
    //fun <R> Iterable<*>.filterIsInstance(): List<R>
    //  指定された型パラメータRのインスタンスであるすべての要素を含むリストを返します。

    // todo
    //fun <C : MutableCollection<in R>, R> Array<*>.filterIsInstanceTo(destination: C, klass: Class<R>): C
    //fun <C : MutableCollection<in R>, R> Iterable<*>.filterIsInstanceTo(destination: C, klass: Class<R>): C
    //  指定されたクラスのインスタンスであるすべての要素を指定された宛先に追加します。
    //fun <R, C : MutableCollection<in R>> Array<*>.filterIsInstanceTo(destination: C): C
    //fun <R, C : MutableCollection<in R>> Iterable<*>.filterIsInstanceTo(destination: C): C
    //  指定された型パラメータRのインスタンスであるすべての要素を指定された宛先に追加します。

    //fun <K, V> Map<out K, V>.filterKeys(predicate: (K) -> Boolean): Map<K, V>
    //  指定された述語に一致するキーを持つすべてのキーと値のペアを含むマップを返します。
    fun sample30() {
        val originalMap = mapOf("key1" to 1, "key2" to 2, "something_else" to 3)
        val filterdMap = originalMap.filterKeys { it.contains("key") }
        println(filterdMap) // {key1=1, key2=2}
        println(originalMap) // {key1=1, key2=2, something_else=3}
        val nonMatchingPredicate: (String) -> Boolean = { it == "key3" }
        val emptyMap = originalMap.filterKeys(nonMatchingPredicate)
        println(emptyMap) // {}
    }

    //fun <T> Array<out T>.filterNot(predicate: (T) -> Boolean): List<T>
    //fun ***Array.filterNot(predicate: (***) -> Boolean): List<***>
    //  与えられた述語と一致しないすべての要素を含むリストを返します。
    //fun <K, V> Map<out K, V>.filterNot(predicate: (Entry<K, V>) -> Boolean): Map<K, V>
    //  指定された述語と一致しないすべてのキーと値のペアを含む新しいマップを返します。
    fun sample31() {
        val originalMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
        val filterdMap = originalMap.filterNot { it.value < 3 }
        println(filterdMap) // {key3=3}
        println(originalMap) // {key1=1, key2=2, key3=3}
        val matchAllPredicate: ((Map.Entry<String, Int>)) -> Boolean = { it.value > 0 }
        val emptyMap = originalMap.filterNot(matchAllPredicate)
        println(emptyMap) // {}
    }

    // todo
    //fun <T : Any> Array<out T?>.filterNotNull(): List<T>
    //fun <T : Any> Iterable<T?>.filterNotNull(): List<T>
    //  nullでないすべての要素を含むリストを返します。

    // todo
    //fun <C : MutableCollection<in T>, T : Any> Array<out T?>.filterNotNullTo(destination: C): C
    //fun <C : MutableCollection<in T>, T : Any> Iterable<T?>.filterNotNullTo(destination: C): C
    //  nullでないすべての要素を指定された宛先に追加します。

    //fun <T, C : MutableCollection<in T>> Array<out T>.filterNotTo(destination: C, predicate: (T) -> Boolean): C
    //fun <C : MutableCollection<in ***>> ***Array.filterNotTo(destination: C, predicate: (***) -> Boolean): C
    //  指定された述語と一致しないすべての要素を指定された宛先に追加します。
    //fun <K, V, M : MutableMap<in K, in V>> Map<out K, V>.filterNotTo(destination: M,
    //    predicate: (Entry<K, V>) -> Boolean): M
    //  指定された述語に一致しないすべてのエントリを指定された宛先に追加します。
    fun sample32() {
        val originalMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
        val destinationMap = mutableMapOf("key40" to 40, "key50" to 50)
        val filteredMap = originalMap.filterNotTo(destinationMap) { it.value < 3 }
        println("destinationMap == filterdMap is ${destinationMap == filteredMap}") // true
        println(filteredMap) // {key40=40, key50=50, key3=3}
        println(destinationMap) // {key40=40, key50=50, key3=3}
        println(originalMap) // {key1=1, key2=2, key3=3}
        val anotherDestinationMap = mutableMapOf("key40" to 40, "key50" to 50)
        val matchAllPredicate: ((Map.Entry<String, Int>)) -> Boolean = { it.value > 0 }
        val filterdMapWithEverythingMatched = originalMap.filterNotTo(anotherDestinationMap, matchAllPredicate)
        println(filterdMapWithEverythingMatched) // {key40=40, key50=50}
    }

    //fun <T, C : MutableCollection<in T>> Array<out T>.filterTo(destination: C, predicate: (T) -> Boolean): C
    //fun <C : MutableCollection<in ***>> ***Array.filterTo(destination: C, predicate: (***) -> Boolean): C
    //  指定された述語に一致するすべての要素を指定された宛先に追加します。
    //fun <K, V, M : MutableMap<in K, in V>> Map<out K, V>.filterTo(destination: M, predicate: (Entry<K, V>) -> Boolean): M
    //  指定された述部に一致するすべての項目を、宛先パラメーターとして指定された変更可能なマップに追加します。
    fun sample33() {
        val originalMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
        val destinationMap = mutableMapOf("key40" to 40, "key50" to 50)
        val filterdMap = originalMap.filterTo(destinationMap) { it.value < 3 }
        println("destinationMap == filterdMap is ${destinationMap == filterdMap}") // true
        println(destinationMap) // {key40=40, key50=50, key1=1, key2=2}
        println(originalMap) // {key1=1, key2=2, key3=3}
        val nonMatchingPredicate: ((Map.Entry<String, Int>)) -> Boolean = { it.value == 0 }
        val anotherDestinationMap = mutableMapOf("key40" to 40, "key50" to 50)
        val filterdMapWithNothingMatched = originalMap.filterTo(anotherDestinationMap, nonMatchingPredicate)
        println(filterdMapWithNothingMatched) // {key40=40, key50=50}
    }

    //fun <K, V> Map<out K, V>.filterValues(predicate: (V) -> Boolean): Map<K, V>
    //  指定された述語に一致する値を持つすべてのキーと値のペアを含むマップを返します。
    fun sample34() {
        val originalMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
        val fileterdMap = originalMap.filterValues { it >= 2 }
        println(fileterdMap) // {key2=2, key3=3}
        println(originalMap) // {key1=1, key2=2, key3=3}
        val nonMatchingPredicate: (Int) -> Boolean = { it == 0 }
        val emptyMap = originalMap.filterValues(nonMatchingPredicate)
        println(emptyMap) // {}
    }

    // todo
    //fun <T> Array<out T>.find(predicate: (T) -> Boolean): T?
    //fun ***Array.find(predicate: (***) -> Boolean): ***?
    //fun <T> Iterable<T>.find(predicate: (T) -> Boolean): T?
    //  指定された述語に一致する最初の要素を返します。見つからない場合はnullを返します。

    // todo
    //fun <T> Array<out T>.findLast(predicate: (T) -> Boolean): T?
    //fun ***Array.findLast(predicate: (***) -> Boolean): ***?
    //fun <T> Iterable<T>.findLast(predicate: (T) -> Boolean): T?
    //fun <T> List<T>.findLast(predicate: (T) -> Boolean): T?
    //  指定された述語に一致する最後の要素を返します。見つからない場合はnullを返します。

    // todo
    //fun <T> Array<out T>.first(): T
    //fun ***Array.first(): ***
    //fun <T> Iterable<T>.first(): T
    //fun <T> List<T>.first(): T
    //  最初の要素を返します。
    //fun <T> Array<out T>.first(predicate: (T) -> Boolean): T
    //fun ***Array.first(predicate: (***) -> Boolean): ***
    //fun <T> Iterable<T>.first(predicate: (T) -> Boolean): T
    //  与えられた述語に一致する最初の要素を返します。

    // todo
    //fun <T> any_array<T>.firstOrNull(): T?
    //fun <T> Iterable<T>.firstOrNull(): T?
    //fun <T> List<T>.firstOrNull(): T?
    //  最初の要素を返します。配列が空の場合はnullを返します。
    //fun <T> Array<out T>.firstOrNull(predicate: (T) -> Boolean): T?
    //fun ***Array.firstOrNull(predicate: (***) -> Boolean): ***?
    //fun <T> Iterable<T>.firstOrNull(predicate: (T) -> Boolean): T?
    //  指定された述語に一致する最初の要素を返します。要素が見つからなかった場合はnullを返します。

    // todo
    //fun <T, R> any_array<T>.flatMap(transform: (T) -> Iterable<R>): List<R>
    //fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R>
    //fun <K, V, R> Map<out K, V>.flatMap(transform: (Entry<K, V>) -> Iterable<R>): List<R>
    //  元の配列の各要素に対して呼び出される変換関数の結果から生成されたすべての要素の単一のリストを返します。

    // todo
    //fun <T, R, C : MutableCollection<in R>> any_array<T>.flatMapTo(destination: C, transform: (T) -> Iterable<R>): C
    //fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapTo(destination: C, transform: (T) -> Iterable<R>): C
    //fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.flatMapTo(destination: C,
    //    transform: (Entry<K, V>) -> Iterable<R>): C
    //  元の配列の各要素に対して呼び出される変換関数の結果から得られたすべての要素を指定された宛先に追加します。

    //fun <T> Array<out Array<out T>>.flatten(): List<T>
    //fun <T> Iterable<Iterable<T>>.flatten(): List<T>
    //  指定された配列のすべての配列からすべての要素の単一のリストを返します。
    fun sample35() {
        val deepArray = arrayOf(
                arrayOf(1),
                arrayOf(2, 3),
                arrayOf(4, 5, 6)
        )
        println(deepArray.flatten()) // [1, 2, 3, 4, 5, 6]

        val deepList = listOf(listOf(1), listOf(2, 3), listOf(4, 5, 6))
        println(deepList.flatten()) // [1, 2, 3, 4, 5, 6]
    }

    // todo
    //fun <T, R> Array<out T>.fold(initial: R, operation: (acc: R, T) -> R): R
    //fun <R> ***Array.fold(initial: R, operation: (acc: R, ***) -> R): R
    //  初期値から始まり、左から右への演算を現在のアキュムレータ値と各要素に適用した値を累積します。
    //fun <T, K, R> Grouping<T, K>.fold(initialValueSelector: (key: K, element: T) -> R,
    //    operation: (key: K, accumulator: R, element: T) -> R): Map<K, R>
    //  キーでグループ化元の要素をグループ化し、以前に累積された値と現在の要素を引数として渡しながら、
    //  各グループの要素に順番に操作を適用し、その結果を新しいマップに保存します。
    //  アキュムレータの初期値は、initialValueSelector関数によって提供されます。
    //fun <T, K, R> Grouping<T, K>.fold(initialValue: R, operation: (accumulator: R, element: T) -> R): Map<K, R>
    //  キーでグループ化元の要素をグループ化し、以前に累積された値と現在の要素を引数として渡しながら、
    //  各グループの要素に順番に操作を適用し、その結果を新しいマップに保存します。
    //  アキュムレータの初期値は、各グループのinitialValueと同じです。

    // todo
    //fun <T, R> any_array<T>.foldIndexed(initial: R, operation: (index: Int, acc: R, T) -> R): R
    //  初期値から始まり、左から右への操作を現在のアキュムレータ値と元の配列のインデックスを持つ各要素に適用する値を累積します。
    //fun <T, R> Iterable<T>.foldIndexed(initial: R, operation: (index: Int, acc: R, T) -> R): R
    //  初期値から始まり、左から右への操作を現在のアキュムレータ値と
    //  元のコレクション内のインデックスを持つ各要素に適用する値を累積します。

    // todo
    //fun <T, R> Array<out T>.foldRight(initial: R, operation: (T, acc: R) -> R): R
    //fun <R> ***Array.foldRight(initial: R, operation: (***, acc: R) -> R): R
    //fun <T, R> List<T>.foldRight(initial: R, operation: (T, acc: R) -> R): R
    //  初期値から始まり、各要素と現在のアキュムレータ値に右から左に演算を適用して値を累積します。

    // todo
    //fun <T, R> any_array<T>.foldRightIndexed(initial: R, operation: (index: Int, T, acc: R) -> R): R
    //  初期値から始まり、元の配列のインデックスと現在のアキュムレータ値を持つ各要素に右から左へ演算を適用する値を累積します。
    //fun <T, R> List<T>.foldRightIndexed(initial: R, operation: (index: Int, T, acc: R) -> R): R
    //  初期値から始まり、元のリストのインデックスと現在のアキュムレータ値を持つ各要素に右から左へ演算を適用して値を累積します。

    // todo
    //fun <T, K, R, M : MutableMap<in K, R>> Grouping<T, K>.foldTo(destination: M,
    //    initialValueSelector: (key: K, element: T) -> R, operation: (key: K, accumulator: R, element: T) -> R): M
    //  グループ化元の要素をキーでグループ化し、各グループの要素に順次累積された値と現在の要素を引数として渡して、
    //  指定された宛先マップに結果を格納します。 アキュムレータの初期値は、initialValueSelector関数によって提供されます。
    //fun <T, K, R, M : MutableMap<in K, R>> Grouping<T, K>.foldTo(destination: M, initialValue: R,
    //    operation: (accumulator: R, element: T) -> R): M
    //  グループ化元の要素をキーでグループ化し、各グループの要素に順次累積された値と現在の要素を引数として渡して、
    //  指定された宛先マップに結果を格納します。 アキュムレータの初期値は、各グループのinitialValueと同じです。

    //fun <T> Array<out T>.forEach(action: (T) -> Unit)
    //fun ***Array.forEach(action: (***) -> Unit)
    //fun <T> Iterable<T>.forEach(action: (T) -> Unit)
    //  各要素に対して所定のアクションを実行します。
    //fun <K, V> Map<out K, V>.forEach(action: (Entry<K, V>) -> Unit)
    //  各エントリに対して所定のアクションを実行します。
    //fun <T> Iterator<T>.forEach(operation: (T) -> Unit)
    //  このIteratorの各要素に対して指定された操作を実行します。
    fun sample36() {
        val iterator = (1..3).iterator()
        if (iterator.hasNext()) {
            iterator.next()
        }
        iterator.forEach {
            println("The element is $it") // 2 3
        }
    }

    //fun <T> Array<out T>.forEachIndexed(action: (index: Int, T) -> Unit)
    //fun ***Array.forEachIndexed(action: (index: Int, ***) -> Unit)
    //fun <T> Iterable<T>.forEachIndexed(action: (index: Int, T) -> Unit)
    //  各要素に対して指定されたアクションを実行し、要素に連続したインデックスを提供します。
    fun sample37() {
        val array = listOf('a', 'b', 'c', 'd', 'e')
        array.forEachIndexed { index, i -> println("$index is $i") }
    }

    // todo
    //operator fun <K, V> Map<out K, V>.get(key: K): V?
    //  指定されたキーに対応する値を返します。キーがマップに存在しない場合はnullを返します。

    // todo
    //fun <K, V> Map<out K, V>.getOrDefault(key: K, defaultValue: V): V
    //  指定されたキーがマッピングされている値を返します。
    //  このマップにキーのマッピングが含まれていない場合はdefaultValueを返します。

    //fun <T> any_array<T>.getOrElse(index: Int, defaultValue: (Int) -> T): T
    //fun <T> List<T>.getOrElse(index: Int, defaultValue: (Int) -> T): T
    //fun <K, V> Map<K, V>.getOrElse(key: K, defaultValue: () -> V): V
    //  指定されたインデックスにある要素、またはインデックスがこの配列の境界外にある場合は
    //  defaultValue関数を呼び出した結果を返します。
    fun sample38() {
        val map = mutableMapOf<String, Int?>()
        println(map.getOrElse("x") { 1 }) // 1
        map["x"] = 3
        println(map.getOrElse("x") { 1 }) // 3
        map["x"] = null
        println(map.getOrElse("x") { 1 }) // 1
    }

    // todo
    //fun <T> any_array<T>.getOrNull(index: Int): T?
    //fun <T> List<T>.getOrNull(index: Int): T?
    //  指定されたインデックスにある要素を返します。インデックスがこの配列の境界外にある場合はnullを返します。

    //fun <K, V> MutableMap<K, V>.getOrPut(key: K, defaultValue: () -> V): V
    //  指定されたキーの値を返します。 キーがマップ内に見つからない場合は、defaultValue関数を呼び出し、
    //  その結果を指定されたキーの下のマップに入れて返します。
    fun sample39() {
        val map = mutableMapOf<String, Int?>()
        println(map.getOrPut("x") { 2 }) // 2
        println(map.getOrPut("x") { 3 }) // 2
        println(map.getOrPut("y") { null }) // null
        println(map.getOrPut("y") { 42 }) // 42
    }

    // todo
    //operator fun <V, V1 : V> Map<in String, V>.getValue(thisRef: Any?, property: KProperty<*>): V1
    //operator fun <V, V1 : V> MutableMap<in String, out V>.getValue(thisRef: Any?, property: KProperty<*>): V1
    //  この読み取り専用マップから、指定されたオブジェクトのプロパティの値を返します。
    //fun <K, V> Map<K, V>.getValue(key: K): V
    //  指定されたキーの値を返します。マップにそのようなキーがない場合は、例外をスローします。

    //fun <T, K> any_array<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>>
    //fun <T, K> Iterable<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>>
    //  指定されたkeySelector関数によって返されたキーによって元の配列の要素をグループ化し、
    //  各要素が対応する要素のリストに関連付けられているマップを返します。
    //fun <T, K, V> any_array<T>.groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>>
    //fun <T, K, V> Iterable<T>.groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>>
    //  要素に適用された指定されたkeySelector関数によって返されたキーによって
    //  元の配列の各要素に適用されたvalueTransform関数によって返された値をグループ化し、
    //  各グループキーが対応する値のリストに関連付けられているマップを返します。
    //fun <T, K, M : MutableMap<in K, MutableList<T>>> any_array<T>.groupByTo(destination: M, keySelector: (T) -> K): M
    //fun <T, K, M : MutableMap<in K, MutableList<T>>> Iterable<T>.groupByTo(destination: M, keySelector: (T) -> K): M
    //  指定されたkeySelector関数によって返されたキーによって元の配列の要素をグループ化し、
    //  各要素に適用し、対応する要素のリストに関連付けられた各グループキーを宛先マップに配置します。
    //fun <T, K, V, M : MutableMap<in K, MutableList<V>>> any_array<T>.groupByTo(destination: M, keySelector: (T) -> K,
    //    valueTransform: (T) -> V): M
    //fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Iterable<T>.groupByTo(destination: M, keySelector: (T) -> K,
    //    valueTransform: (T) -> V): M
    //  要素に適用された指定されたkeySelector関数によって返されたキーによって、
    //  元の配列の各要素に適用されたvalueTransform関数によって返された値をグループ化し、
    //  対応する値のリストに関連付けられた各グループキーを宛先マップに配置します。
    fun sample40() {
        val words = listOf("a", "abc", "ab", "def", "abcd")
        val byLength = words.groupBy { it.length }
        println(byLength.keys) // [1, 3, 2, 4]
        println(byLength.values) // [[a], [abc, def], [ab], [abcd]]
        val mutableByLength = words.groupByTo(mutableMapOf()) { it.length }
        println("mutableByLength == byLength is ${mutableByLength == byLength}") // true

        val nameToTeam = listOf("Alice" to "Marketing", "Bob" to "Sales", "Carol" to "Marketing")
        val namesByTeam = nameToTeam.groupBy({ it.second }, { it.first })
        println(namesByTeam) // {Marketing=[Alice, Carol], Sales=[Bob]}
        val mutableNamesByTeam = nameToTeam.groupByTo(HashMap(), { it.second }, { it.first })
        println("mutableNamesByTeam == namesByTeam is ${mutableNamesByTeam == namesByTeam}") // true
    }

    //fun <T, K> Array<out T>.groupingBy(keySelector: (T) -> K): Grouping<T, K>
    //  指定されたkeySelector関数を使用して各要素からキーを抽出し、
    //  後でgroup-fold操作の1つで使用する配列から、グループ化ソースを作成します。
    //fun <T, K> Iterable<T>.groupingBy(keySelector: (T) -> K): Grouping<T, K>
    //  指定されたkeySelector関数を使用して各要素からキーを抽出し、
    //  後でgroup-and-fold操作の1つで使用するコレクションからGroupingソースを作成します。
    fun sample41() {
        val words = "one two three four five six seven eight nine ten".split(' ')
        val frequenciesByFirstChar = words.groupingBy { it.first() }.eachCount()
        println(frequenciesByFirstChar) // {o=1, t=3, f=2, s=2, e=1, n=1}
        val moreWords = "eleven twelve".split(' ')
        val moreFrequencies = moreWords.groupingBy { it.first() }.eachCountTo(frequenciesByFirstChar.toMutableMap())
        println(moreFrequencies) //{o=1, t=4, f=2, s=2, e=2, n=1}
    }

    //fun <K, V> hashMapOf(): HashMap<K, V>
    //  空の新しいHashMapを返します。
    //fun <K, V> hashMapOf(vararg pairs: Pair<K, V>): HashMap<K, V>
    //  指定された内容の新しいHashMapを返します。最初のコンポーネントがキーで、2番目が値です。
    fun sample42() {
        val map = hashMapOf(1 to "x", 2 to "y", -1 to "zz")
        println(map) // {-1=zz, 1=x, 2=y}
    }

    // todo
    //fun <T> hashSetOf(): HashSet<T>
    //  空の新しいHashSetを返します。
    //fun <T> hashSetOf(vararg elements: T): HashSet<T>
    //  指定された要素を持つ新しいHashSetを返します。

    // todo
    //fun <T> any_array<T>.indexOf(element: T): Int
    //fun <T> Iterable<T>.indexOf(element: T): Int
    //fun <T> List<T>.indexOf(element: T): Int
    //  要素の最初のインデックスを返します。配列に要素が含まれていない場合は-1を返します。

    // todo
    //fun <T> any_array<T>.indexOfFirst(predicate: (T) -> Boolean): Int
    //fun <T> Iterable<T>.indexOfFirst(predicate: (T) -> Boolean): Int
    //fun <T> List<T>.indexOfFirst(predicate: (T) -> Boolean): Int
    //  指定された述語に一致する最初の要素のインデックスを返します。配列にそのような要素が含まれていない場合は-1を返します。

    // todo
    //fun <T> any_array<T>.indexOfLast(predicate: (T) -> Boolean): Int
    //fun <T> Iterable<T>.indexOfLast(predicate: (T) -> Boolean): Int
    //fun <T> List<T>.indexOfLast(predicate: (T) -> Boolean): Int
    //  指定された述語に一致する最後の要素のインデックスを返します。配列にそのような要素が含まれていない場合は-1を返します。

    // todo
    //infix fun <T> Array<out T>.intersect(other: Iterable<T>): Set<T>
    //infix fun ***Array.intersect(other: Iterable<***>): Set<***>
    //infix fun <T> Iterable<T>.intersect(other: Iterable<T>): Set<T>
    //  このセットと指定されたコレクションの両方に含まれるすべての要素を含むセットを返します。

    // todo
    //fun <T> any_array<T>.isEmpty(): Boolean
    //  配列が空の場合はtrueを返します。

    //fun <T> any_array<T>.isNotEmpty(): Boolean
    //fun <T> Collection<T>.isNotEmpty(): Boolean
    //fun <K, V> Map<out K, V>.isNotEmpty(): Boolean
    //  配列が空でない場合はtrueを返します。
    fun sample43() {
        val empty = emptyList<Any>()
        println(empty.isNotEmpty()) // false
        val collection = listOf('a', 'b', 'c')
        println(collection.isNotEmpty()) // true
    }

    //operator fun <T> Iterator<T>.iterator(): Iterator<T>
    //operator fun <K, V> Map<out K, V>.iterator(): Iterator<Entry<K, V>>
    //operator fun <K, V> MutableMap<K, V>.iterator(): MutableIterator<MutableEntry<K, V>>
    //  指定されたイテレータ自体を返します。 これにより、forループ内のイテレータのインスタンスを使用できます。
    fun sample44() {
        val mutableList = mutableListOf(1, 2, 3)
        val mutableIterator = mutableList.iterator()
        for (e in mutableIterator) {
            if (e % 2 == 0) {
                //forループで反復されるのと同じイテレータなので、
                //ConcurrentModificationExceptionを取得せずにイテレータから項目を削除できます
                mutableIterator.remove()
            }
            println("The element is $e") // The element is 1
            // The element is 2
            // The element is 3
        }

        val map = mapOf("beverage" to 2.7, "meal" to 12.4, "dessert" to 5.8)
        for ((key, value) in map) {
            println("$key - $value") // beverage - 2.7
            // meal - 12.4
            // dessert - 5.8
        }
    }

    //fun <T, A> Array<out T>.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "",
    //    postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...",
    //    transform: (T) -> CharSequence = null): A
    //fun <A> ***Array.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "",
    //    postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...",
    //    transform: (***) -> CharSequence = null): A
    //fun <T, A> Iterable<T>.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "",
    //    postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...",
    //    transform: (T) -> CharSequence = null): A
    //  区切り文字を使用して区切られ、指定された接頭辞と接尾辞が指定されている場合は、それを使用して区切られたすべての要素の文字列
    fun sample45() {
        val sb = StringBuilder("An existing string and a list: ")
        val number = listOf(1, 2, 3)
        println(number.joinTo(sb, prefix = "[", postfix = "]").toString()) // An existing string and a list: [1, 2, 3]
        val lotOfNumbers: Iterable<Int> = 1..100
        val firstNumbers = StringBuilder("First five numbers: ")
        println(lotOfNumbers.joinTo(firstNumbers, limit = 5).toString()) // First five numbers: 1, 2, 3, 4, 5, ...
    }

    //fun <T> Array<out T>.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "",
    //    postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...",
    //    transform: (T) -> CharSequence = null): String
    //fun ***Array.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "",
    //    limit: Int = -1, truncated: CharSequence = "...", transform: (***) -> CharSequence = null): String
    //fun <T> Iterable<T>.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "",
    //    postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...",
    //    transform: (T) -> CharSequence = null): String
    //  区切り文字を使用して区切られ、指定された接頭辞と接尾辞が指定されている場合はそれを使用して
    //  区切られたすべての要素から文字列
    fun sample46() {
        val number = listOf(1, 2, 3, 4, 5, 6)
        println(number.joinToString()) // 1, 2, 3, 4, 5, 6
        println(number.joinToString(prefix = "[", postfix = "]")) // [1, 2, 3, 4, 5, 6]
        println(number.joinToString(prefix = "<", postfix = ">", separator = "*")) // <1*2*3*4*5*6>
        val chars = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q')
        println(chars.joinToString(limit = 5, truncated = "...!") { it.toUpperCase().toString() }) // A, B, C, D, E, ...!
    }

    // todo
    //fun <T> Array<out T>.last(): T
    //fun ***Array.last(): ***
    //fun <T> Iterable<T>.last(): T
    //fun <T> List<T>.last(): T
    //  最後の要素を返します。
    //fun <T> Array<out T>.last(predicate: (T) -> Boolean): T
    //fun ***Array.last(predicate: (***) -> Boolean): ***
    //fun <T> Iterable<T>.last(predicate: (T) -> Boolean): T
    //fun <T> List<T>.last(predicate: (T) -> Boolean): T
    //  指定された述語に一致する最後の要素を返します。

    // todo
    //fun <T> any_array<T>.lastIndexOf(element: T): Int
    //fun <T> Iterable<T>.lastIndexOf(element: T): Int
    //fun <T> List<T>.lastIndexOf(element: T): Int
    //  要素の最後のインデックスを返します。配列に要素が含まれていない場合は-1を返します。

    // todo
    //fun <T> any_array<T>.lastOrNull(): T?
    //  最後の要素を返します。配列が空の場合はnullを返します。
    //fun <T> Array<out T>.lastOrNull(predicate: (T) -> Boolean): T?
    //fun ***Array.lastOrNull(predicate: (***) -> Boolean): ***?
    //fun <T> Iterable<T>.lastOrNull(): T?
    //fun <T> List<T>.lastOrNull(): T?
    //  指定された述語に一致する最後の要素を返します。見つからない場合はnullを返します。

    //fun <K, V> linkedMapOf(): LinkedHashMap<K, V>
    //  空の新しいLinkedHashMapを返します。
    //fun <K, V> linkedMapOf(vararg pairs: Pair<K, V>): LinkedHashMap<K, V>
    //  指定された内容を持つ新しいLinkedHashMapを返します。
    //  最初のコンポーネントがキーで、2番目が値であるペアのリストとして与えられます。
    fun sample47() {
        val map = linkedMapOf(1 to "x", 2 to "y", -1 to "zz")
        println(map) // {1=x, 2=y, -1=zz}
    }

    // todo
    //fun <T> linkedSetOf(): LinkedHashSet<T>
    //  空の新しいLinkedHashSetを返します。
    //fun <T> linkedSetOf(vararg elements: T): LinkedHashSet<T>
    //  指定された要素を持つ新しいLinkedHashSetを返します。 セットの要素は、指定された順序で反復されます。

    // todo
    //fun <V> linkedStringMapOf(vararg pairs: Pair<String, V>): LinkedHashMap<String, V>
    //  LinkedHashMapの特殊な実装をStringキーで構築します。
    //  これは、キーをハッシングすることなくJSオブジェクトのプロパティとして格納します。

    // todo
    //fun linkedStringSetOf(vararg elements: String): LinkedHashSet<String>
    //  LinkedHashSetの特殊な実装の新しいインスタンスを、指定されたString要素で作成します。
    //  これらの要素は、キーをハッシングすることなくJSオブジェクトのプロパティとして組み込みます。

    //fun <T> listOf(element: T): List<T>
    //fun <T> listOf(vararg elements: T): List<T>
    //  指定されたオブジェクト要素のみを含む不変のリストを返します。 返されるリストは直列化可能です。
    //fun <T> listOf(): List<T>
    //  空の読み取り専用リストを返します。 返されるリストは直列化可能（JVM）です。
    fun sample48() {
        val list = listOf('a')
        println(list) // [a]
        println(list.size) // 1

        val otherlist = listOf("a1", "b1", "c1")
        println(otherlist.contains("a1")) // true
        println(otherlist.indexOf("b1")) // 1
        println(otherlist[2]) // c1
    }

    //fun <T : Any> listOfNotNull(element: T?): List<T>
    //  単一の指定された要素（nullでない場合）または要素がnullの場合は
    //  空のリストのいずれかの新しい読み取り専用リストを返します。 返されるリストは直列化可能（JVM）です。
    //fun <T : Any> listOfNotNull(vararg elements: T?): List<T>
    //  指定された要素のうち、null以外の新しい読み取り専用リストを返します。 返されるリストは直列化可能（JVM）です。
    fun sample49() {
        val empty = listOfNotNull<Any>(null)
        println(empty) // []
        val singleton = listOfNotNull(42)
        println(singleton) // [42]
        val list = listOfNotNull(1, null, 2, null, 3)
        println(list) // [1, 2, 3]
    }

    // todo
    //fun <T, R> any_array<T>.map(transform: (T) -> R): List<R>
    //fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>
    //fun <K, V, R> Map<out K, V>.map(transform: (Entry<K, V>) -> R): List<R>
    //  指定された変換関数を元の配列の各要素に適用した結果を含むリストを返します。

    // todo
    //fun <T, R> any_array<T>.mapIndexed(transform: (index: Int, T) -> R): List<R>
    //fun <T, R> Iterable<T>.mapIndexed(transform: (index: Int, T) -> R): List<R>
    //  指定された変換関数を各要素に適用した結果と元の配列のインデックスを含むリストを返します。

    // todo
    //fun <T, R : Any> Array<out T>.mapIndexedNotNull(transform: (index: Int, T) -> R?): List<R>
    //fun <T, R : Any> Iterable<T>.mapIndexedNotNull(transform: (index: Int, T) -> R?): List<R>
    //  指定された変換関数を各要素に適用した結果と元の配列のインデックスを含むnull以外の結果のみを含むリストを返します。

    // todo
    //fun <T, R : Any, C : MutableCollection<in R>> Array<out T>.mapIndexedNotNullTo(destination: C,
    //    transform: (index: Int, T) -> R?): C
    //fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapIndexedNotNullTo(destination: C,
    //    transform: (index: Int, T) -> R?): C
    //  指定された変換関数を元の配列の各要素とそのインデックスに適用し、nullでない結果のみを指定された出力先に追加します。

    // todo
    //fun <T, R, C : MutableCollection<in R>> any_array<T>.mapIndexedTo(destination: C,
    //    transform: (index: Int, T) -> R): C
    //fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapIndexedTo(destination: C,
    //    transform: (index: Int, T) -> R): C
    //  指定された変換関数を元の配列の各要素とそのインデックスに適用し、結果を指定された出力先に追加します。

    //fun <K, V, R> Map<out K, V>.mapKeys(transform: (Entry<K, V>) -> R): Map<R, V>
    //  このマップの各エントリとこのマップの値に変換関数を適用して得られたキーを持つエントリを持つ新しいマップを返します。
    fun sample50() {
        val map1 = mapOf("beer" to 2.7, "bisquit" to 5.8)
        val map2 = map1.mapKeys { it.key.length }
        println(map2) // {4=2.7, 7=5.8}
        val map3 = map1.mapKeys { it.key.take(1) }
        println(map3) // {b=5.8}
    }

    // todo
    //fun <K, V, R, M : MutableMap<in R, in V>> Map<out K, V>.mapKeysTo(destination: M, transform: (Entry<K, V>) -> R): M
    //  指定されたデスティネーションマップに、
    //  このマップの各エントリとこのマップの値に変換関数を適用して得られたキーを持つエントリを設定します。

    // todo
    //fun <T, R : Any> Array<out T>.mapNotNull(transform: (T) -> R?): List<R>
    //fun <T, R : Any> Iterable<T>.mapNotNull(transform: (T) -> R?): List<R>
    //fun <K, V, R : Any> Map<out K, V>.mapNotNull(transform: (Entry<K, V>) -> R?): List<R>
    //  指定された変換関数を元の配列の各要素に適用した結果がNULLでない結果のみを含むリストを返します。

    // todo
    //fun <T, R : Any, C : MutableCollection<in R>> Array<out T>.mapNotNullTo(destination: C,
    //    transform: (T) -> R?): C
    //fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapNotNullTo(destination: C,
    //    transform: (T) -> R?): C
    //fun <K, V, R : Any, C : MutableCollection<in R>> Map<out K, V>.mapNotNullTo(destination: C,
    //    transform: (Entry<K, V>) -> R?): C
    //  指定された変換関数を元の配列の各要素に適用し、null以外の結果のみを指定された宛先に追加します。

    //fun <K, V> mapOf(pair: Pair<K, V>): Map<K, V>
    //fun <K, V> mapOf(vararg pairs: Pair<K, V>): Map<K, V>
    //  指定されたキーのみを指定された値にマッピングする不変のマップを返します。
    //fun <K, V> mapOf(): Map<K, V>
    //  空の読み取り専用マップを返します。
    fun sample51() {
        val map = mapOf(1 to "x", 2 to "y", -1 to "zz")
        println(map) // {1=x, 2=y, -1=zz}
    }

    // todo
    //fun <T, R, C : MutableCollection<in R>> any_array<T>.mapTo(destination: C, transform: (T) -> R): C
    //fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapTo(destination: C, transform: (T) -> R): C
    //fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.mapTo(destination: C, transform: (Entry<K, V>) -> R): C
    //  指定された変換関数を元の配列の各要素に適用し、結果を指定された出力先に追加します。

    //fun <K, V, R> Map<out K, V>.mapValues(transform: (Entry<K, V>) -> R): Map<K, R>
    //  このマップのキーを持つエントリと、このマップの各エントリに変換関数を適用して得られた値を持つ新しいマップを返します。
    fun sample52() {
        val map1 = mapOf("beverage" to 2.7, "meal" to 12.4)
        val map2 = map1.mapValues { it.value.toString() + "$" }
        println(map2) // {beverage=2.7$, meal=12.4$}
    }

    // todo
    //fun <K, V, R, M : MutableMap<in K, in R>> Map<out K, V>.mapValuesTo(destination: M, transform: (Entry<K, V>) -> R): M
    //  指定されたデスティネーションマップに、
    //  このマップのキーを持つエントリと、このマップの各エントリに変換関数を適用した値を設定します。

    // todo
    //fun Array<out Double>.max(): Double?
    //fun <T : Comparable<T>> Array<out T>.max(): T?
    //fun ***Array.max(): ***?
    //fun Iterable<Double>.max(): Double?
    //fun <T : Comparable<T>> Iterable<T>.max(): T?
    //  最大の要素を返します。要素がない場合はnullを返します。

    // todo
    //fun <T, R : Comparable<R>> Array<out T>.maxBy(selector: (T) -> R): T?
    //fun <R : Comparable<R>> ***Array.maxBy(selector: (***) -> R): ***?
    //fun <T, R : Comparable<R>> Iterable<T>.maxBy(selector: (T) -> R): T?
    //  指定された関数の最大値を返す最初の要素を返します。要素がない場合はnullを返します。
    //fun <K, V, R : Comparable<R>> Map<out K, V>.maxBy(selector: (Entry<K, V>) -> R): Entry<K, V>?
    //  指定された関数の最大値を返す最初のエントリを返します。エントリがない場合はnullを返します。

    // todo
    //fun <T> Array<out T>.maxWith(comparator: Comparator<in T>): T?
    //fun ***Array.maxWith(comparator: Comparator<in ***>): ***?
    //fun <T> Iterable<T>.maxWith(comparator: Comparator<in T>): T?
    //fun <K, V> Map<out K, V>.maxWith(comparator: Comparator<in Entry<K, V>>): Entry<K, V>?
    //  指定されたコンパレータに従って最大値を持つ最初の要素を返します。要素がない場合はnullを返します。

    // todo
    //fun Array<out Double>.min(): Double?
    //fun <T : Comparable<T>> Array<out T>.min(): T?
    //fun ***Array.min(): ***?
    //fun Iterable<Double>.min(): Double?
    //fun <T : Comparable<T>> Iterable<T>.min(): T?
    //  最小の要素を返します。要素がない場合はnullを返します。

    // todo
    //fun <T, R : Comparable<R>> Array<out T>.minBy(selector: (T) -> R): T?
    //fun <R : Comparable<R>> ***Array.minBy(selector: (***) -> R): ***?
    //fun <T, R : Comparable<R>> Iterable<T>.minBy(selector: (T) -> R): T?
    //fun <K, V, R : Comparable<R>> Map<out K, V>.minBy(selector: (Entry<K, V>) -> R): Entry<K, V>?
    //  指定された関数の最小値を生成する最初の要素を返します。要素がない場合はnullを返します。

    // todo
    //fun <T> Array<out T>.minWith(comparator: Comparator<in T>): T?
    //fun ***Array.minWith(comparator: Comparator<in ***>): ***?
    //fun <T> Iterable<T>.minWith(comparator: Comparator<in T>): T?
    //fun <K, V> Map<out K, V>.minWith(comparator: Comparator<in Entry<K, V>>): Entry<K, V>?
    //  指定されたコンパレータに従って最小の値を持つ最初の要素を返します。要素がない場合はnullを返します。

    // todo
    //operator fun <T> Iterable<T>.minus(element: T): List<T>
    //operator fun <T> Iterable<T>.minus(elements: Array<out T>): List<T>
    //operator fun <T> Iterable<T>.minus(elements: Iterable<T>): List<T>
    //operator fun <T> Iterable<T>.minus(elements: Sequence<T>): List<T>
    //  指定された要素が最初に出現しない元のコレクションのすべての要素を含むリストを返します。
    //operator fun <T> Set<T>.minus(element: T): Set<T>
    //operator fun <T> Set<T>.minus(elements: Array<out T>): Set<T>
    //operator fun <T> Set<T>.minus(elements: Iterable<T>): Set<T>
    //operator fun <T> Set<T>.minus(elements: Sequence<T>): Set<T>
    //  指定された要素を除く元の集合のすべての要素を含む集合を返します。
    //operator fun <K, V> Map<out K, V>.minus(key: K): Map<K, V>
    //operator fun <K, V> Map<out K, V>.minus(keys: Iterable<K>): Map<K, V>
    //operator fun <K, V> Map<out K, V>.minus(keys: Array<out K>): Map<K, V>
    //operator fun <K, V> Map<out K, V>.minus(keys: Sequence<K>): Map<K, V>
    //  指定されたキーコレクションにキーが含まれているエントリを除く、元のマップのすべてのエントリを含むマップを返します。

    // todo
    //operator fun <K, V> MutableMap<K, V>.minusAssign(key: K)
    //operator fun <K, V> MutableMap<K, V>.minusAssign(keys: Iterable<K>)
    //operator fun <K, V> MutableMap<K, V>.minusAssign(keys: Array<out K>)
    //operator fun <K, V> MutableMap<K, V>.minusAssign(keys: Sequence<K>)
    //  この変更可能なマップから、指定されたキーを持つエントリを削除します。
    //operator fun <T> MutableCollection<in T>.minusAssign(element: T)
    //operator fun <T> MutableCollection<in T>.minusAssign(elements: Iterable<T>)
    //operator fun <T> MutableCollection<in T>.minusAssign(elements: Array<T>)
    //operator fun <T> MutableCollection<in T>.minusAssign(elements: Sequence<T>)
    //  この変更可能なコレクションから指定された要素の単一のインスタンスを削除します。

    // todo
    //fun <T> Iterable<T>.minusElement(element: T): List<T>
    //  指定された要素が最初に出現しない元のコレクションのすべての要素を含むリストを返します。
    //fun <T> Set<T>.minusElement(element: T): Set<T>
    //  指定された要素を除く元の集合のすべての要素を含む集合を返します。

    //fun <T> mutableListOf(): MutableList<T>
    //  空の新しいMutableListを返します。
    //fun <T> mutableListOf(vararg elements: T): MutableList<T>
    //  指定された要素を持つ新しいMutableListを返します。
    fun sample53() {
        val list = mutableListOf<Int>()
        println(list.isEmpty()) // true
        list.addAll(listOf(1, 2, 3))
        println(list)  // [1, 2, 3]
        list += listOf(4, 5)
        println(list) // [1, 2, 3, 4, 5]
    }

    //fun <K, V> mutableMapOf(): MutableMap<K, V>
    //  空の新しいMutableMapを返します。
    //fun <K, V> mutableMapOf(vararg pairs: Pair<K, V>): MutableMap<K, V>
    //  指定された内容を持つ新しいMutableMapを返します。最初のコンポーネントがキーで、2番目が値です。
    fun sample54() {
        val map = mutableMapOf<Int, Any?>()
        println(map.isEmpty()) // true
        map[1] = "x"
        map[2] = 1.05
        println(map) // {1=x, 2=1.05}
        val otherMap = mutableMapOf(1 to "x", 2 to "y", -1 to "zz")
        println(otherMap) // {1=x, 2=y, -1=zz}
        otherMap[1] = "a"
        println(otherMap) // {1=a, 2=y, -1=zz}
    }

    // todo
    //fun <T> mutableSetOf(): MutableSet<T>
    //  空の新しいMutableSetを返します。
    //fun <T> mutableSetOf(vararg elements: T): MutableSet<T>
    //  指定された要素を持つ新しいMutableSetを返します。 セットの要素は、指定された順序で反復されます。

    //fun <T> any_array<T>.none(): Boolean
    //fun <T> Iterable<T>.none(): Boolean
    //fun <K, V> Map<out K, V>.none(): Boolean
    //  配列に要素がない場合はtrueを返します。
    //fun <T> Array<out T>.none(predicate: (T) -> Boolean): Boolean
    //fun ***Array.none(predicate: (***) -> Boolean): Boolean
    //fun <T> Iterable<T>.none(predicate: (T) -> Boolean): Boolean
    //fun <K, V> Map<out K, V>.none(predicate: (Entry<K, V>) -> Boolean): Boolean
    //  指定された述語に一致する要素がない場合はtrueを返します。
    fun sample55() {
        val emptyList = emptyList<Int>()
        println(emptyList.none()) // true
        val nonEmptyList = listOf("one", "two", "three")
        println(nonEmptyList.none()) // false

        val isEven: (Int) -> Boolean = { it % 2 == 0 }
        val zeroToTen = 0..10
        println(zeroToTen.none { isEven(it) }) // false
        println(zeroToTen.none(isEven)) // false
        val adds = zeroToTen.map { it * 2 + 1 }
        println(adds.none { isEven(it) }) // true
        val emptyL = emptyList<Int>()
        println(emptyL.none { true }) // true
    }

    // todo
    //fun <T, C : Iterable<T>> C.onEach(action: (T) -> Unit): C
    //  各要素に対して所定のアクションを実行し、後でそのコレクション自体を返します。
    //fun <K, V, M : Map<out K, V>> M.onEach(action: (Entry<K, V>) -> Unit): M
    //  各エントリに対して所定のアクションを実行し、その後にマップ自体を返します。

    //actual fun <T> Array<out T>?.orEmpty(): Array<out T>
    //fun <T> Collection<T>?.orEmpty(): Collection<T>
    //fun <T> List<T>?.orEmpty(): List<T>
    //fun <K, V> Map<K, V>?.orEmpty(): Map<K, V>
    //fun <T> Set<T>?.orEmpty(): Set<T>
    //  nullでない場合は配列を返し、そうでない場合は空の配列を返します。
    fun sample56() {
        val nullArray: Array<Any>? = null
        println(nullArray.orEmpty().contentToString()) // []
        val array: Array<Char>? = arrayOf('a', 'b', 'c')
        println(array.orEmpty().contentToString()) // [a, b, c]
        val nullList: List<Any>? = null
        println(nullList.orEmpty()) // []
        val nonNullList: List<Char>? = listOf('a', 'b', 'c')
        println(nonNullList.orEmpty()) // [a, b, c]
    }

    // todo
    //fun <T> any_array<T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>>
    //fun <T> Iterable<T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>>
    //  元の配列をリストのペアに分割します。最初のリストには述語が真である要素が含まれ、
    //  2番目のリストには述語がfalseを返す要素が含まれます。

    // todo
    //operator actual fun <T> any_array<T>.plus(element: T): Array<T>
    //operator actual fun <T> any_array<T>.plus(elements: Collection<T>): Array<T>
    //operator actual fun <T> any_array<T>.plus(elements: Array<out T>): Array<T>
    //  元の配列のすべての要素と指定された要素を含む配列を返します。
    //operator fun <T> Iterable<T>.plus(element: T): List<T>
    //operator fun <T> Collection<T>.plus(element: T): List<T>
    //operator fun <T> Iterable<T>.plus(elements: Array<out T>): List<T>
    //operator fun <T> Collection<T>.plus(elements: Array<out T>): List<T>
    //operator fun <T> Iterable<T>.plus(elements: Iterable<T>): List<T>
    //operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T>
    //operator fun <T> Iterable<T>.plus(elements: Sequence<T>): List<T>
    //operator fun <T> Collection<T>.plus(elements: Sequence<T>): List<T>
    //  元のコレクションのすべての要素を含み、指定された要素を含むリストを返します。
    //operator fun <T> Set<T>.plus(element: T): Set<T>
    //operator fun <T> Set<T>.plus(elements: Array<out T>): Set<T>
    //operator fun <T> Set<T>.plus(elements: Iterable<T>): Set<T>
    //operator fun <T> Set<T>.plus(elements: Sequence<T>): Set<T>
    //  元のセットのすべての要素を含むセットを返します。まだセットに入っていない場合は、指定された要素を返します。
    //operator fun <K, V> Map<out K, V>.plus(pair: Pair<K, V>): Map<K, V>
    //operator fun <K, V> Map<out K, V>.plus(pairs: Iterable<Pair<K, V>>): Map<K, V>
    //operator fun <K, V> Map<out K, V>.plus(pairs: Array<out Pair<K, V>>): Map<K, V>
    //operator fun <K, V> Map<out K, V>.plus(pairs: Sequence<Pair<K, V>>): Map<K, V>
    //operator fun <K, V> Map<out K, V>.plus(map: Map<out K, V>): Map<K, V>
    // 指定されたキーと値のペアからこのマップへのエントリを置換または追加することによって、新しい読み取り専用マップを作成します。

    // todo
    //operator fun <K, V> MutableMap<in K, in V>.plusAssign(pair: Pair<K, V>)
    //operator fun <K, V> MutableMap<in K, in V>.plusAssign(pairs: Iterable<Pair<K, V>>)
    //operator fun <K, V> MutableMap<in K, in V>.plusAssign(pairs: Array<out Pair<K, V>>)
    //operator fun <K, V> MutableMap<in K, in V>.plusAssign(pairs: Sequence<Pair<K, V>>)
    //operator fun <K, V> MutableMap<in K, in V>.plusAssign(map: Map<K, V>)
    //  指定されたペアをこの変更可能なマップに追加または置換します。
    //operator fun <T> MutableCollection<in T>.plusAssign(element: T)
    //operator fun <T> MutableCollection<in T>.plusAssign(elements: Iterable<T>)
    //operator fun <T> MutableCollection<in T>.plusAssign(elements: Array<T>)
    //operator fun <T> MutableCollection<in T>.plusAssign(elements: Sequence<T>)
    //  指定された要素をこの変更可能なコレクションに追加します。

    // todo
    //actual fun <T> Array<T>.plusElement(element: T): Array<T>
    //fun <T> Iterable<T>.plusElement(element: T): List<T>
    //fun <T> Collection<T>.plusElement(element: T): List<T>
    //fun <T> Set<T>.plusElement(element: T): Set<T>
    //  元の配列のすべての要素と指定された要素を含む配列を返します。

    // todo
    //fun <K, V> MutableMap<in K, in V>.putAll(pairs: Array<out Pair<K, V>>)
    //fun <K, V> MutableMap<in K, in V>.putAll(pairs: Iterable<Pair<K, V>>)
    //fun <K, V> MutableMap<in K, in V>.putAll(pairs: Sequence<Pair<K, V>>)
    //  指定されたすべてのペアをこのMutableMapに格納します。ペアの最初のコンポーネントをキー、2番目の値を値として使用します。

    // todo
    //fun <S, T : S> Array<out T>.reduce(operation: (acc: S, T) -> S): S
    //fun ***Array.reduce(operation: (acc: ***, ***) -> ***): ***
    //fun <S, T : S> Iterable<T>.reduce(operation: (acc: S, T) -> S): S
    //  最初の要素から始まり、左から右の演算を現在のアキュムレータ値と各要素に適用する値を累積します。
    //fun <S, T : S, K> Grouping<T, K>.reduce(operation: (key: K, accumulator: S, element: T) -> S): Map<K, S>
    //  グループ化元の要素をキーでグループ化し、グループの2番目の要素から順番に各グループの要素に還元操作を適用し、
    //  以前に累積された値と現在の要素を引数として渡し、結果を新しいマップに格納します。
    //  アキュムレータの初期値は、グループの最初の要素です。

    // todo
    //fun <S, T : S> any_array<S>.reduceIndexed(operation: (index: Int, acc: S, T) -> S): S
    //fun <S, T : S> Iterable<T>.reduceIndexed(operation: (index: Int, acc: S, T) -> S): S
    //  最初の要素から始まり、左から右への操作を現在のアキュムレータ値と元の配列の
    //  インデックスを持つ各要素に適用する値を累積します。

    // todo
    //fun <S, T : S> Array<out T>.reduceRight(operation: (T, acc: S) -> S): S
    //fun ***Array.reduceRight(operation: (***, acc: ***) -> ***): ***
    //fun <S, T : S> List<T>.reduceRight(operation: (T, acc: S) -> S): S
    //  最後の要素から始まり、各要素と現在のアキュムレータ値に右から左に操作を適用する値を累積します。

    // todo
    //fun <S, T : S> any_array<S>.reduceRightIndexed(operation: (index: Int, T, acc: S) -> S): S
    //fun <S, T : S> List<T>.reduceRightIndexed(operation: (index: Int, T, acc: S) -> S): S
    //  最後の要素から始まり、元の配列のインデックスと現在のアキュムレータ値を持つ各要素に右から左に演算を適用する値を累積します。

    // todo
    //fun <S, T : S, K, M : MutableMap<in K, S>> Grouping<T, K>.reduceTo(destination: M,
    //    operation: (key: K, accumulator: S, element: T) -> S): M
    //  グループ化元の要素をキーでグループ化し、グループの2番目の要素から順番に各グループの要素に還元演算を適用し、
    //  以前に累積された値と現在の要素を引数として渡し、指定された宛先マップに結果を格納します。
    //  アキュムレータの初期値は、グループの最初の要素です。

    // todo
    //fun <K, V> MutableMap<out K, out V>.remove(key: K, value: V): Boolean
    //  現在指定された値にマップされている場合にのみ、指定されたキーのエントリを削除します。
    //fun <K, V> MutableMap<out K, V>.remove(key: K): V?
    //  このマップから、指定されたキーとそれに対応する値を削除します。
    //fun <T> MutableCollection<out T>.remove(element: T): Boolean
    //  指定された要素の単一のインスタンスをこのコレクションから削除します（存在する場合）。
    //fun <T> MutableList<T>.remove(index: Int): T
    //  指定されたインデックスにある要素をこのリストから削除します。 KotlinではMutableList.removeAt関数を代わりに使うべきです。

    // todo
    //fun <T> MutableCollection<out T>.removeAll(elements: Collection<T>): Boolean
    //fun <T> MutableCollection<in T>.removeAll(elements: Iterable<T>): Boolean
    //fun <T> MutableCollection<in T>.removeAll(elements: Sequence<T>): Boolean
    //fun <T> MutableCollection<in T>.removeAll(elements: Array<out T>): Boolean
    //  指定されたコレクションにも含まれる、このコレクションのすべての要素を削除します。
    //fun <T> MutableIterable<T>.removeAll(predicate: (T) -> Boolean): Boolean
    //fun <T> MutableList<T>.removeAll(predicate: (T) -> Boolean): Boolean
    //  このMutableIterableから、指定された述語に一致するすべての要素を削除します。

    // todo
    //fun <T : Any> Array<T?>.requireNoNulls(): Array<T>
    //fun <T : Any> Iterable<T?>.requireNoNulls(): Iterable<T>
    //fun <T : Any> List<T?>.requireNoNulls(): List<T>
    //  すべてのnull以外の要素を含む元のコレクションを返し、null要素がある場合はIllegalArgumentExceptionをスローします。

    // todo
    //fun <T> MutableCollection<out T>.retainAll(elements: Collection<T>): Boolean
    //fun <T> MutableCollection<in T>.retainAll(elements: Iterable<T>): Boolean
    //fun <T> MutableCollection<in T>.retainAll(elements: Array<out T>): Boolean
    //fun <T> MutableCollection<in T>.retainAll(elements: Sequence<T>): Boolean
    //  指定されたコレクションに含まれる、このコレクション内の要素のみを保持します。
    //fun <T> MutableIterable<T>.retainAll(predicate: (T) -> Boolean): Boolean
    //fun <T> MutableList<T>.retainAll(predicate: (T) -> Boolean): Boolean
    //  このMutableIterableの、指定された述語に一致する要素のみを保持します。

    // todo
    //actual fun <T> MutableList<T>.reverse()
    //fun <T> any_array<T>.reverse()
    //  リスト内の要素をインプレースで元に戻します。

    // todo
    //fun <T> Array<out T>.reversed(): List<T>
    //fun ***Array.reversed(): List<***>
    //fun <T> Iterable<T>.reversed(): List<T>
    //  逆順の要素を含むリストを返します。

    // todo
    //fun <T> any_array<T>.reversedArray(): Array<T>
    //  この配列の要素を逆順に持つ配列を返します。

    // todo
    //operator fun <K, V> MutableMap<K, V>.set(key: K, value: V)
    //  変更可能なマップに値を格納するためにインデックス演算子を使用できます。

    //fun <T> setOf(element: T): Set<T>
    //  指定されたオブジェクト要素のみを含む不変集合を返します。 返されるセットは直列化可能です。
    //fun <T> setOf(vararg elements: T): Set<T>
    //  指定された要素を持つ新しい読み取り専用セットを返します。 セットの要素は、指定された順序で反復されます。
    //  返されるセットは直列化可能（JVM）です。
    //fun <T> setOf(): Set<T>
    //  空の読み取り専用セットを返します。 返されるセットは直列化可能（JVM）です。
    fun sample57() {
        val set1 = setOf(1, 2, 3)
        val set2 = setOf(3, 2, 1)
        println(set1) // [1, 2, 3]
        println(set2) // [3, 2, 1]
        println(set1 == set2) // true
    }

    // todo
    //operator fun <V> MutableMap<in String, in V>.setValue(thisRef: Any?, property: KProperty<*>, value: V)
    //  指定されたオブジェクトのプロパティの値をこの変更可能なマップに格納します。

    // todo
    //actual fun <T> MutableList<T>.shuffle()
    //  この変更可能なリスト内の要素をランダムにシャッフルします。
    //fun <T> MutableList<T>.shuffle(random: Random)
    //  ランダム性のソースとして指定されたランダムインスタンスを使用して、この変更可能リスト内の要素をランダムにシャッフルします。

    // todo
    //actual fun <T> Iterable<T>.shuffled(): List<T>
    //  このリストの要素がランダムにシャッフルされた新しいリストを返します。
    //fun <T> Iterable<T>.shuffled(random: Random): List<T>
    //  指定された乱数インスタンスを乱数のソースとして使用してランダムにシャッフルされた、このリストの要素を持つ新しいリストを返します。

    // todo
    //fun <T> any_array<T>.single(): T
    //  単一の要素を返します。配列が空であるか複数の要素がある場合は例外をスローします。
    //fun <T> Array<out T>.single(predicate: (T) -> Boolean): T
    //fun ***Array.single(predicate: (***) -> Boolean): ***
    //fun <T> Iterable<T>.single(predicate: (T) -> Boolean): T
    //  指定された述語に一致する単一の要素を返します。一致する要素が1つ以上存在しない場合は例外をスローします。
    //fun <T> Iterable<T>.single(): T
    //fun <T> List<T>.single(): T
    //  単一の要素を返します。または、コレクションが空であるか複数の要素を持つ場合は例外をスローします。

    // todo
    //fun <T> any_array<T>.singleOrNull(): T?
    //  単一の要素を返します。配列が空であるか複数の要素を持つ場合はnullを返します。
    //fun <T> Array<out T>.singleOrNull(predicate: (T) -> Boolean): T?
    //fun ***eArray.singleOrNull(predicate: (***) -> Boolean): ***?
    //fun <T> Iterable<T>.singleOrNull(predicate: (T) -> Boolean): T?
    //  指定された述語に一致する単一の要素を返します。要素が見つからなかった場合、または複数の要素が見つかった場合はnullを返します。
    //fun <T> Iterable<T>.singleOrNull(): T?
    //fun <T> List<T>.singleOrNull(): T?
    //  単一の要素を返します。コレクションが空であるか複数の要素を持つ場合はnullを返します。

    // todo
    //fun <T> Array<out T>.slice(indices: IntRange): List<T>
    //fun ***Array.slice(indices: IntRange): List<***>
    //fun <T> List<T>.slice(indices: IntRange): List<T>
    //  指定されたインデックス範囲のインデックスにある要素を含むリストを返します。
    //fun <T> Array<out T>.slice(indices: Iterable<Int>): List<T>
    //fun ***Array.slice(indices: Iterable<Int>): List<***>
    //fun <T> List<T>.slice(indices: Iterable<Int>): List<T>
    //  指定されたインデックスの要素を含むリストを返します。

    // todo
    //fun <T> any_array<T>.sliceArray(indices: Collection<Int>): Array<T>
    //fun <T> any_array<T>.sliceArray(indices: IntRange): Array<T>
    //  指定されたインデックスでこの配列の要素を含む配列を返します。

    // todo
    //actual fun IntArray.sort()
    //  配列をインプレースでソートします。
    //actual fun <T : Comparable<T>> Array<out T>.sort()
    //  要素の自然順序に従ってインプレースを並べ替えます。
    //fun <T> any_array<T>.sort(fromIndex: Int = 0, toIndex: Int = size)
    //  配列内のある範囲をインプレースでソートします。
    //actual fun <T : Comparable<T>> MutableList<T>.sort()
    //  リスト内の要素を、自然なソート順序に従ってインプレースでソートします。
    //fun <T> any_array<T>.sort(comparison: (a: T, b: T) -> Int)
    //  指定された比較関数で指定された順序に従って、その配列をソートします。
    //fun <T> MutableList<T>.sort(comparator: Comparator<in T>)
    //fun <T> MutableList<T>.sort(comparison: (T, T) -> Int)
    //  Deprecated: Use sortWith(comparator) instead.

    // todo
    //fun <T, R : Comparable<R>> Array<out T>.sortBy(selector: (T) -> R?)
    //fun <T, R : Comparable<R>> MutableList<T>.sortBy(selector: (T) -> R?)
    //  指定されたセレクタ関数によって返される値の自然な並べ替え順に従って、配列内の要素をその場でソートします。

    // todo
    //fun <T, R : Comparable<R>> Array<out T>.sortByDescending(selector: (T) -> R?)
    //fun <T, R : Comparable<R>> MutableList<T>.sortByDescending(selector: (T) -> R?)
    //  指定されたセレクタ関数によって返される値の自然な並べ替え順序に従って、インプレース降順の配列内の要素を並べ替えます。

    // todo
    //actual fun <T> any_array<T>.sortWith(comparator: Comparator<in T>)
    //  指定されたコンパレータによって指定された順序に従って、その場で配列をソートします。
    //fun <T> Array<out T>.sortWith(comparator: Comparator<in T>, fromIndex: Int = 0, toIndex: Int = size)
    //  指定されたコンパレータを使用して、その配列内のある範囲をソートします。
    //actual fun <T> MutableList<T>.sortWith(comparator: Comparator<in T>)
    //  コンパレータで指定された順序に従ってインプレースのリスト内の要素をソートします。

    // todo
    //fun <T : Comparable<T>> Array<out T>.sorted(): List<T>
    //fun ***Array.sorted(): List<***>
    //fun <T : Comparable<T>> Iterable<T>.sorted(): List<T>
    //  自然なソート順に従ってソートされたすべての要素のリストを返します。

    // todo
    //fun <T : Comparable<T>> any_array<T>.sortedArray(): Array<T>
    //  この配列のすべての要素が自然なソート順序に従ってソートされた配列を返します。

    // todo
    //fun <T : Comparable<T>> any_array<T>.sortedArrayDescending(): Array<T>
    //  この配列のすべての要素が自然な並べ替え順序に従って降順にソートされた配列を返します。

    // todo
    //fun <T> Array<out T>.sortedArrayWith(comparator: Comparator<in T>): Array<out T>
    //  この配列のすべての要素が指定されたコンパレータに従ってソートされた配列を返します。

    // todo
    //fun <T, R : Comparable<R>> Array<out T>.sortedBy(selector: (T) -> R?): List<T>
    //fun <R : Comparable<R>> ***Array.sortedBy(selector: (***) -> R?): List<***>
    //fun <T, R : Comparable<R>> Iterable<T>.sortedBy(selector: (T) -> R?): List<T>
    //  指定されたセレクタ関数によって返された値の自然なソート順に従ってソートされたすべての要素のリストを返します。

    // todo
    //fun <T, R : Comparable<R>> Array<out T>.sortedByDescending(selector: (T) -> R?): List<T>
    //fun <R : Comparable<R>> ***Array.sortedByDescending(selector: (***) -> R?): List<***>
    //fun <T, R : Comparable<R>> Iterable<T>.sortedByDescending(selector: (T) -> R?): List<T>
    //  指定されたセレクタ関数によって返された値の自然な並べ替え順序に従って降順にソートされたすべての要素のリストを返します。

    // todo
    //fun <T : Comparable<T>> Array<out T>.sortedDescending(): List<T>
    //fun ***Array.sortedDescending(): List<***>
    //fun <T : Comparable<T>> Iterable<T>.sortedDescending(): List<T>
    //  自然なソート順に従って降順にソートされたすべての要素のリストを返します。

    //fun <K : Comparable<K>, V> sortedMapOf(vararg pairs: Pair<K, V>): SortedMap<K, V>
    //  指定された内容を持つ新しいSortedMapを返します。最初の値がキー、2番目が値のペアのリストとして与えられます。
    fun sample58() {
        val map = sortedMapOf(Pair("c", 3), Pair("b", 2), Pair("a", 1))
        println(map.keys) // [a, b, c]
        println(map.values) // [1, 2, 3]
    }

    // todo
    //fun <T> sortedSetOf(vararg elements: T): TreeSet<T>
    //  指定された要素を持つ新しいjava.util.SortedSetを返します。
    //fun <T> sortedSetOf(comparator: Comparator<in T>, vararg elements: T): TreeSet<T>
    //  指定されたコンパレータおよび要素を持つ新しいjava.util.SortedSetを返します。

    // todo
    //fun <T> Array<out T>.sortedWith(comparator: Comparator<in T>): List<T>
    //fun ***Array.sortedWith(comparator: Comparator<in ***>): List<***>
    //fun <T> Iterable<T>.sortedWith(comparator: Comparator<in T>): List<T>
    //  指定されたコンパレータに従ってソートされたすべての要素のリストを返します。

    // todo
    //fun <V> stringMapOf(vararg pairs: Pair<String, V>): HashMap<String, V>
    //  StringをキーにしたHashMapの特殊な実装を構築します。
    //  これは、キーをハッシングすることなくJSオブジェクトのプロパティとして格納します。

    // todo
    //fun stringSetOf(vararg elements: String): HashSet<String>
    //  HashSetの特殊な実装の新しいインスタンスを、指定されたString要素で作成します。
    //  この要素は、ハッシュしないでJSオブジェクトのプロパティとしてキーを構成します。

    // todo
    //infix fun <T> any_array<T>.subtract(other: Iterable<T>): Set<T>
    //infix fun <T> Iterable<T>.subtract(other: Iterable<T>): Set<T>
    //  この配列に含まれ、指定されたコレクションに含まれていないすべての要素を含むセットを返します。

    // todo
    //fun Array<out ***>.sum(): Int
    //fun ***Array.sum(): Int
    //  配列内のすべての要素の合計を返します。
    //fun Iterable<***>.sum(): Int
    //  コレクション内のすべての要素の合計を返します。

    // todo
    //fun <T> any_array<T>.sumBy(selector: (T) -> Int): Int
    //fun <T> Iterable<T>.sumBy(selector: (T) -> Int): Int
    //  配列の各要素に適用されたセレクタ関数によって生成されたすべての値の合計を返します。

    // todo
    //fun <T> any_array<T>.sumByDouble(selector: (T) -> Double): Double
    //fun <T> Iterable<T>.sumByDouble(selector: (T) -> Double): Double
    //  配列の各要素に適用されたセレクタ関数によって生成されたすべての値の合計を返します。

    //fun <T> Array<out T>.take(n: Int): List<T>
    //fun ***Array.take(n: Int): List<***>
    //fun <T> Iterable<T>.take(n: Int): List<T>
    //  最初のn個の要素を含むリストを返します。
    //fun <T> Array<out T>.takeLast(n: Int): List<T>
    //fun ***Array.takeLast(n: Int): List<***>
    //fun <T> List<T>.takeLast(n: Int): List<T>
    //  最後のn個の要素を含むリストを返します。
    //fun <T> Array<out T>.takeLastWhile(predicate: (T) -> Boolean): List<T>
    //fun ***Array.takeLastWhile(predicate: (***) -> Boolean): List<***>
    //fun <T> List<T>.takeLastWhile(predicate: (T) -> Boolean): List<T>
    //  指定された述語を満たす最後の要素を含むリストを返します。
    //fun <T> Array<out T>.takeWhile(predicate: (T) -> Boolean): List<T>
    //fun ***Array.takeWhile(predicate: (***) -> Boolean): List<***>
    //fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T>
    //  与えられた述語を満たす最初の要素を含むリストを返します。
    fun sample59() {
        val chars = ('a'..'z').toList()
        println(chars.take(3)) // [a, b, c]
        println(chars.takeWhile { it < 'f' }) // [a, b, c, d, e]
        println(chars.takeLast(2)) // [y, z]
        println(chars.takeLastWhile { it > 'w' }) // [x, y, z]
    }

    // todo
    //fun Array<out Boolean>.toBooleanArray(): BooleanArray
    //fun Collection<Boolean>.toBooleanArray(): BooleanArray
    //  この汎用配列のすべての要素を含むブール値の配列を返します。

    // todo
    //fun Array<out Byte>.toByteArray(): ByteArray
    //fun Collection<Byte>.toByteArray(): ByteArray
    //  この汎用配列のすべての要素を含むByteの配列を返します。

    // todo
    //fun Array<out Char>.toCharArray(): CharArray
    //fun Collection<Char>.toCharArray(): CharArray
    //  この汎用配列のすべての要素を含むCharの配列を返します。

    // todo
    //fun <T, C : MutableCollection<in T>> Array<out T>.toCollection(destination: C): C
    //fun <C : MutableCollection<in ***>> ***Array.toCollection(destination: C): C
    //fun <T, C : MutableCollection<in T>> Iterable<T>.toCollection(destination: C): C
    //  指定されたデスティネーションコレクションにすべての要素を追加します。

    // todo
    //fun Array<out Double>.toDoubleArray(): DoubleArray
    //fun Collection<Double>.toDoubleArray(): DoubleArray
    //  この汎用配列のすべての要素を含むDoubleの配列を返します。

    // todo
    //fun Array<out Float>.toFloatArray(): FloatArray
    //fun Collection<Float>.toFloatArray(): FloatArray
    //  この汎用配列のすべての要素を含むFloatの配列を返します。

    // todo
    //fun <T> Array<out T>.toHashSet(): HashSet<T>
    //fun ByteArray.toHashSet(): HashSet<Byte>
    //fun <T> Iterable<T>.toHashSet(): HashSet<T>
    //  すべての要素のHashSetを返します。

    // todo
    //fun Array<out Int>.toIntArray(): IntArray
    //fun Collection<Int>.toIntArray(): IntArray
    //  この汎用配列のすべての要素を含むIntの配列を返します。

    // todo
    //fun <T> Array<out T>.toList(): List<T>
    //fun ByteArray.toList(): List<Byte>
    //fun <T> Iterable<T>.toList(): List<T>
    //fun <K, V> Map<out K, V>.toList(): List<Pair<K, V>>
    //  すべての要素を含むListを返します。

    // todo
    //fun Array<out Long>.toLongArray(): LongArray
    //fun Collection<Long>.toLongArray(): LongArray
    //  この汎用配列のすべての要素を含むLongの配列を返します。

    // todo
    //fun <K, V> Iterable<Pair<K, V>>.toMap(): Map<K, V>
    //fun <K, V> Array<out Pair<K, V>>.toMap(): Map<K, V>
    //fun <K, V> Sequence<Pair<K, V>>.toMap(): Map<K, V>
    //fun <K, V> Map<out K, V>.toMap(): Map<K, V>
    //  指定されたペアのコレクションからすべてのキーと値のペアを含む新しいマップを返します。
    //fun <K, V, M : MutableMap<in K, in V>> Iterable<Pair<K, V>>.toMap(destination: M): M
    //fun <K, V, M : MutableMap<in K, in V>> Array<out Pair<K, V>>.toMap(destination: M): M
    //fun <K, V, M : MutableMap<in K, in V>> Sequence<Pair<K, V>>.toMap(destination: M): M
    //fun <K, V, M : MutableMap<in K, in V>> Map<out K, V>.toMap(destination: M): M
    //  指定されたペアのコレクションから、キーと値のペアを持つ変更可能なマップを生成して返します。

    // todo
    //fun <T> any_array<T>.toMutableList(): MutableList<T>
    //fun <T> Iterable<T>.toMutableList(): MutableList<T>
    //fun <T> Collection<T>.toMutableList(): MutableList<T>
    //  この配列のすべての要素で満たされたMutableListを返します。

    // todo
    //fun <K, V> Map<out K, V>.toMutableMap(): MutableMap<K, V>
    //  元のマップからすべてのキーと値のペアを含む新しい可変マップを返します。

    // todo
    //fun <T> any_array<T>.toMutableSet(): MutableSet<T>
    //fun <T> Iterable<T>.toMutableSet(): MutableSet<T>
    //  指定された配列とは異なるすべての要素を含む可変セットを返します。

    // todo
    //fun <K, V> Entry<K, V>.toPair(): Pair<K, V>
    //  キーを最初のコンポーネントにし、値を秒にしてエントリをペアに変換します。

    //fun Map<String, String>.toProperties(): Properties
    //  このMapをPropertiesオブジェクトに変換します。
    fun sample60() {
        val map = mapOf("x" to "value of A", "y" to "value of B")
        val props = map.toProperties()
        println(props.getProperty("x")) // value of A
        println(props.getProperty("y", "fail")) // value of B
        println(props.getProperty("z", "fail")) // fail
    }

    // todo
    //fun <T> Array<out T>.toSet(): Set<T>
    //fun ***Array.toSet(): Set<***>
    //fun <T> Iterable<T>.toSet(): Set<T>
    //  すべての要素のSetを返します。

    // todo
    //fun Array<out Short>.toShortArray(): ShortArray
    //fun Collection<Short>.toShortArray(): ShortArray
    //  この汎用配列のすべての要素を含むShortの配列を返します。

    //fun <K : Comparable<K>, V> Map<out K, V>.toSortedMap(): SortedMap<K, V>
    //  このマップをSortedMapに変換して、反復順序がキー順になるようにします。
    //fun <K, V> Map<out K, V>.toSortedMap(comparator: Comparator<in K>): SortedMap<K, V>
    //  指定されたコンパレータを使用してこのマップをSortedMapに変換し、
    //  反復順序がコンパレータによって定義された順序になるようにします。
    fun sample61() {
        val map = mapOf(Pair("c", 3), Pair("b", 2), Pair("d", 1))
        val sorted = map.toSortedMap()
        println(sorted.keys) // [b, c, d]
        println(sorted.values) // [2, 3, 1]

        val otherMap = mapOf(Pair("abc", 1), Pair("c", 3), Pair("bd", 4), Pair("bc", 2))
        val otherSorted = otherMap.toSortedMap(compareBy<String> { it.length }.thenBy { it })
        println(otherSorted.keys) // [c, bc, bd, abc]
    }

    // todo
    //fun <T : Comparable<T>> Array<out T>.toSortedSet(): SortedSet<T>
    //fun ByteArray.toSortedSet(): SortedSet<Byte>
    //fun <T> Array<out T>.toSortedSet(comparator: Comparator<in T>): SortedSet<T>
    //fun <T : Comparable<T>> Iterable<T>.toSortedSet(): SortedSet<T>
    //fun <T> Iterable<T>.toSortedSet(comparator: Comparator<in T>): SortedSet<T>
    //  すべての要素のSortedSetを返します。

    //fun ByteArray.toString(charset: Charset): String
    //  指定された文字セットを使用して、このバイト配列の内容を文字列に変換します。
    fun sample62() {
        val charset = Charsets.UTF_8
        val byteArray = "Hello".toByteArray(charset)
        println(byteArray.contentToString()) // [72, 101, 108, 108, 111]
        println(byteArray.toString(charset)) // Hello
    }

    // todo
    //infix fun <T> Array<out T>.union(other: Iterable<T>): Set<T>
    //infix fun ByteArray.union(other: Iterable<Byte>): Set<Byte>
    //infix fun <T> Iterable<T>.union(other: Iterable<T>): Set<T>
    //  両方のコレクションのすべての異なる要素を含むセットを返します。

    //fun <T, R> Array<out Pair<T, R>>.unzip(): Pair<List<T>, List<R>>
    //fun <T, R> Iterable<Pair<T, R>>.unzip(): Pair<List<T>, List<R>>
    //  最初のリストがこの配列の各ペアの最初の値から構築され、
    //  2番目のリストがこの配列の各ペアの2番目の値から構築されるリストのペアを返します。
    fun sample63() {
        val array = arrayOf(1 to 'a', 2 to 'b', 3 to 'c')
        println(array.unzip()) // ([1, 2, 3], [a, b, c])
        val list = listOf(1 to 'a', 2 to 'b', 3 to 'c')
        println(list.unzip()) // ([1, 2, 3], [a, b, c])
    }

    //fun <T> Iterable<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false): List<List<T>>
    //  各スナップショットがリストである、指定されたステップでこのコレクションに沿ってスライドする、
    //  指定されたサイズのウィンドウのスナップショットのリストを返します。
    //fun <T, R> Iterable<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false,
    //    transform: (List<T>) -> R): List<R>
    //  指定された変換関数を、指定されたステップでこのコレクションに沿ってスライドする、
    //  指定されたサイズのウィンドウ上のビューを表す各リストに適用した結果のリストを返します。
    fun sample64() {
        val sequence = generateSequence(1) { it + 1 }
        val windows = sequence.windowed(size = 5, step = 1)
        println(windows.take(4).toList()) // [[1, 2, 3, 4, 5], [2, 3, 4, 5, 6], [3, 4, 5, 6, 7], [4, 5, 6, 7, 8]]

        val moreSparseWindows = sequence.windowed(size = 5, step = 3)
        println(moreSparseWindows.take(4).toList()) // [[1, 2, 3, 4, 5], [4, 5, 6, 7, 8], [7, 8, 9, 10, 11], [10, 11, 12, 13, 14]]

        val fullWindows = sequence.take(10).windowed(size = 5, step = 3)
        println(fullWindows.toList()) // [[1, 2, 3, 4, 5], [4, 5, 6, 7, 8]]

        val patialWindows = sequence.take(10).windowed(size = 5, step = 3, partialWindows = true)
        println(patialWindows.toList()) // [[1, 2, 3, 4, 5], [4, 5, 6, 7, 8], [7, 8, 9, 10], [10]]

        val dataPoints = sequenceOf(10, 15, 18, 25, 29, 21, 14, 8, 5)
        val averaged = dataPoints.windowed(size = 4, step = 1, partialWindows = true) { windows -> windows.average() }
        println(averaged.toList()) // [17.0, 21.75, 23.25, 22.25, 18.0, 12.0, 9.0, 6.5, 5.0]
        val averagedNoPatialWindows = dataPoints.windowed(size = 4, step = 1).map { it.average() }
        println(averagedNoPatialWindows.toList()) // [17.0, 21.75, 23.25, 22.25, 18.0, 12.0]
    }

    // todo
    //fun <K, V> Map<K, V>.withDefault(defaultValue: (key: K) -> V): Map<K, V>
    //fun <K, V> MutableMap<K, V>.withDefault(defaultValue: (key: K) -> V): MutableMap<K, V>
    //  指定された関数defaultValueで指定された暗黙のデフォルト値を持つ、この読み取り専用マップのラッパーを返します。

    //fun <T> any_array<T>.withIndex(): Iterable<IndexedValue<T>>
    //fun <T> Iterable<T>.withIndex(): Iterable<IndexedValue<T>>
    //fun <T> Iterator<T>.withIndex(): Iterator<IndexedValue<T>>
    //  元の配列の各要素に対してIndexedValueの遅延Iterableを返します。
    fun sample65() {
        val iterator = ('a'..'c').iterator()
        for ((index, value) in iterator.withIndex()) {
            println("The element at $index is $value")
        }
    }

    //infix fun <T, R> any_array<T>.zip(other: Array<out R>): List<Pair<T, R>>
    //infix fun <T, R> any_array<T>.zip(other: Iterable<R>): List<Pair<T, R>>
    //infix fun <T, R> Iterable<T>.zip(other: Array<out R>): List<Pair<T, R>>
    //infix fun <T, R> Iterable<T>.zip(other: Iterable<R>): List<Pair<T, R>>
    //  この配列の要素と同じインデックスを持つ配列から構築されたペアのリストを返します。 返されるリストの長さは最短のコレクションです。
    //fun <T, R, V> any_array<T>.zip(other: Array<out R>, transform: (a: T, b: R) -> V): List<V>
    //fun <V> any_array<V>.zip(other: ByteArray, transform: (a: Byte, b: Byte) -> V): List<V>
    //fun <T, R, V> any_array<T>.zip(other: Iterable<R>, transform: (a: T, b: R) -> V): List<V>
    //fun <T, R, V> Iterable<T>.zip(other: Array<out R>, transform: (a: T, b: R) -> V): List<V>
    //fun <T, R, V> Iterable<T>.zip(other: Iterable<R>, transform: (a: T, b: R) -> V): List<V>
    //  この配列の要素と同じインデックスを持つ他の配列から構築された値のリストを、
    //  要素の各ペアに適用された変換関数を使用して返します。 返されるリストの長さは最短のコレクションです。
    fun sample66() {
        val listA = listOf("a", "b", "c")
        val listB = listOf(1, 2, 3, 4)
        println(listA zip listB) //[(a, 1), (b, 2), (c, 3)]
        val result = listA.zip(listB) { a, b -> "$a$b" }
        println(result) // [a1, b2, c3]
    }

    //fun <T> Iterable<T>.zipWithNext(): List<Pair<T, T>>
    //  このコレクション内の2つの隣接する要素のペアのリストを返します。
    //fun <T, R> Iterable<T>.zipWithNext(transform: (a: T, b: T) -> R): List<R>
    //  指定された変換関数をこのコレクション内の2つの隣接する要素の各ペアに適用した結果を含むリストを返します。
    val letters = ('a'..'f').toList()
    val pairs = letters.zipWithNext()
    println(letters) // [a, b, c, d, e, f]
    println(pairs) // [(a, b), (b, c), (c, d), (d, e), (e, f)]
    val values = listOf(1, 4, 9, 16, 25, 36)
    val deltas = values.zipWithNext { a, b -> b - a }
    println(deltas) // [3, 5, 7, 9, 11]
}
