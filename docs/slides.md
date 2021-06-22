## Eclipse Collections

* An alternative to JDK Collections
* Created in 2004 in Goldman Sachs
* Open sourced as GS Collections in 2012
    * Free as in beer - Goldman only contributors
* Moved to Eclipse Foundation at end of 2015
    * Free as in speech - anyone can contribute after they sign ECA
* Design goals
    * Rich API for collections
    * Memory savings 
    * Performance

    
## Today
* 2 Project Leads (Nikhil and Don)
* 5 Active Committers 
* Member of [OpenJDK Quality Outreach program](https://wiki.openjdk.java.net/display/quality/Quality+Outreach)
  * Found/reported issue in [JDK 15 with CharSequence](https://stuartmarks.wordpress.com/2020/09/22/incompatibilities-with-jdk-15-charsequence-isempty/)
* Current Release: [10.4](https://github.com/eclipse/eclipse-collections/releases/tag/10.4.0)
* Planned Release: 11.0 release soon after JDK 17 released (September 2021)


## Learning
* Eclipse Collections Katas
    * [Company kata](https://github.com/eclipse/eclipse-collections-kata/tree/master/company-kata) (largest API coverage) 
    * [Pet kata](https://github.com/eclipse/eclipse-collections-kata/tree/master/pet-kata) (good API coverage)
    * [Candy kata](https://github.com/eclipse/eclipse-collections-kata/tree/master/candy-kata) (example of Bag)
    * [Converter Method Kata](https://github.com/eclipse/eclipse-collections-kata/tree/master/converter-method-kata) (covers all to(Type), toImmutable(Type) methods)
    * [Top Methods Kata] - Covers 25 of favorite methods
* [Reference Guide](https://github.com/eclipse/eclipse-collections/blob/master/docs/guide.md)
* [Jackson Support](https://github.com/eclipse/eclipse-collections/blob/master/docs/jackson.md)

    
## Unique Data Structures

* Bag
* Multimap
* HashingStrategy Data Structures
* Primitive Data Structures


### Bag

* Unorderd collection which allows duplicates
* Similar to ```Map<K, Integer```
* Useful for counting things by a function (```countBy```)

```java
String words = "one two Two three Three THREE four FOUR Four FoUr";
MutableBag<String> bag = Bags.mutable.with(words.split(" "));
Bag<String> lowercaseWords = bag.countBy(String::toLowerCase);

Assertions.assertEquals(1, lowercaseWords.occurrencesOf("one"));
Assertions.assertEquals(2, lowercaseWords.occurrencesOf("two"));
Assertions.assertEquals(3, lowercaseWords.occurrencesOf("three"));
Assertions.assertEquals(4, lowercaseWords.occurrencesOf("four"));

```

### Multimap

* Map-like data structure that allows multiple values per key
* Similar to ```Map<K, Collection<V>```
* Useful for grouping things by a function (```groupBy```)

```java
String words = "The quick brown fox jumps over the lazy dog";
MutableList<String> list = Lists.mutable.with(words.split(" "));
Multimap<Character, String> multimap = list.collect(String::toLowerCase)
        .groupBy(each -> each.charAt(0));

Assertions.assertEquals(Lists.mutable.with("the", "the"), multimap.get('t'));
Assertions.assertEquals(Lists.mutable.empty(), multimap.get('a'));
```


### HashingStrategy
* HashingStrategy is an interface with two methods to implement
  * ```int computeHashCode(E object)```
  * ```boolean equals(E object1, E object2)```
* Useful for creating hash based data structures without requiring dedicated keys
* Types
  * ```UnifiedSetWithHashingStrategy```
  * ```UnifiedMapWithHashingStrategy```
  * ```HashBagWithHashingStrategy```


### UnifiedSetWithHashingStrategy Example

```java
MutableSet<Customer> setByName = HashingStrategySets.mutable.with(
        HashingStrategies.chain(
                HashingStrategies.fromFunction(Customer::getLastName),
                HashingStrategies.fromFunction(Customer::getFirstName),
                HashingStrategies.fromFunction(Customer::getMiddleInitial)));

Assertions.assertTrue(setByName.add(new Customer("Donald", "A", "Duck")));
Assertions.assertFalse(setByName.add(new Customer("Donald", "A", "Duck")));
Assertions.assertTrue(setByName.add(new Customer("Mickey", "Mouse", "T")));
```


### UnifiedMapWithHashingStrategy Example

```java
MutableMap<String, String> caseInsensitiveMap =
        HashingStrategyMaps.mutable.<String, String>with(
                HashingStrategies.fromFunction(String::toLowerCase))
                .withKeyValue("one", "1")
                .withKeyValue("Two", "2")
                .withKeyValue("THREE", "3");

Assertions.assertEquals("1", caseInsensitiveMap.get("ONE"));
Assertions.assertEquals("2", caseInsensitiveMap.get("tWO"));
Assertions.assertEquals("3", caseInsensitiveMap.get("three"));
```


### HashBagWithHashingStrategy Example


```java
String words = "one two Two three Three THREE four FOUR Four FoUr";
List<String> list = Arrays.asList(words.split(" "));
MutableBag<String> caseInsensitiveBag =
        HashingStrategyBags.mutable.<String>with(
                HashingStrategies.fromFunction(String::toLowerCase))
                .withAll(list);

Assertions.assertEquals(1, caseInsensitiveBag.occurrencesOf("ONE"));
Assertions.assertEquals(2, caseInsensitiveBag.occurrencesOf("two"));
Assertions.assertEquals(3, caseInsensitiveBag.occurrencesOf("THREE"));
Assertions.assertEquals(4, caseInsensitiveBag.occurrencesOf("four"));
```


## Hidden Treasures

* Symmetry (select -> reject)
* +With patterns
* +By patterns
* selectInstancesOf
* Set operations
* chunk
* zip


