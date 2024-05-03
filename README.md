# ufomc-cache

What is UfoMC-Loading Cache and what does it do?
-
The UfoMc-Loading Cache is a cache allowing you to eazily implement your database methodes and beeing customize by your wishes.
It has the feture "fetch" and "write" wich allow you to just add your methodes from the dataBase you are using just ones.
The feture "fetch" is used when you try to get an object from the cache wich is not inserted already. 
The feture "write" is used when an cache object is beeing kicked out of the cache to instantly write it into your DB.

Who is the developer?
-
I am Matteo from germany. I manly code open source projects in java in hope to help some of you out there.
Feel free to use the ufomc cache where and when ever you want. 
If you want you can give me a credit. 
Also you can contact me via discord (ufo.dev) if you have any questions about my person, the cache or anything else or you want to give me ideas for the project or upcomming things :D

Usage:
-
> cache example
```java
//the long is just an example. just insert the value you want to use. THIS MAY BE AN JAVA OBJECT LIKE USER-OBJECT...
LoadingCache<TestObject> cache = new CacheBuilder<TestObject>()
        //set the maxSize (kb)
        .setMaxSize(2)//added soon
        //set the time wich the entries have before expiering
        .setExpireAfter(5)
        //sets the timestemp for the expiering time (Minutes, Seconds...)
        .setTimeStamp(TimeUnit.MINUTES)
        //sets the max entries (how many entries the cache sould have at max). after expiereing the 'write' methode is executed!
        .setMaxEntries(5)//added soon
        //add your fetch and write methode
        .build(new CacheImpMethods<>() {
            @Override
            public TestObject fetch(String key) {
                //your methode the fetch (get) an object with a key from your dataBase
                return dataBase.get(key);
            }

            @Override
            public void write(String key, TestObject value) {
                //your methode to write the value with your key to your dataBase
                dataBase.put(key, value);
            }
        });
```

> recieve data
```java
cache.get("your-key");
```

> put data
```
cache.put("your-key", new TestObject("ash", 19, UUID.randomUUID()));
//this has to be an instance of your given object in the generic
```

> for each
```java
//you have to define your key and value wich you are going to use in the loop here
cache.forEach((key, value) -> {
  //here you can do anything with your object
  System.out.println(value.getUUID());
});
```
