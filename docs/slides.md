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
    * Set
    * Map
    * Bag
* Primitive Data Structures

## Hidden Treasures

* Symmetry (select -> reject)
* +With patterns
* +By patterns
* selectInstancesOf
* Set operations
* chunk
* zip
