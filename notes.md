# Notes

## OOP principle

- Encapsulation
- Abstraction
- Inheritance
- Polymorphism

## Abstraction and Encapsulation

### Abstraction

- Implementor defines the data/functional abstractions using lower-level data and processes
- Client uses the high-level data-type and methods  
- Data abstraction: abstract away lower level data
- Functional abstraction: abstract away lower level computation

- Cyclic dependencies
  - occur when two or more modules reference each other.
    - This could be a direct reference (A -> B -> A), or indirect (A -> B -> C -> A).
  - If you depend on me setting something up, and I depend on you setting something up, how do we start?
  - Circular class references create high coupling; both classes must be recompiled every time either of them is changed.
  - middleman
  
### Encapsulation

- packaging
  - Package related data and behaviour in a self-contained unit
  - Classes provide a way to package low level data
  - Object — an abstraction of closely-related data and behavior
    - properties / attributes / fields
    - Constructor
    - Methods
      - Method Signature consists of the method name and the parameter list.
  - When an object is created using new, a reference to the instantiated object is returned
- information hiding
  - Hide information/data from the client and allowing access only through methods provided
  - Access Modifiers
    - Prevent access to lower level details by another object using private access modifiers
  - Encapsulation isn’t merely about restricting access to properties and providing accessors (or getters)
  - Immutability
    - Methods should return new immutable objects
    - To ensure immutability, make all instance fields final
    - why?
      - Immutability is important in multi-threaded programs, because then you know that one thread won't corrupt a value used in another thread.
      - It also means much better encapsulation. You can pass those objects around and share them and you never, ever have to worry that someone changes your object's state.
      - It also leads to more readable and maintainable code. No need to care about other users of the object.

## Inheritance and Polymorphism

### Inheritance

- "is-a" relationship, inherit attributes and methods from one class to another
  - subclass (child) - the class that inherits from another class
  - superclass (parent) - the class being inherited from
- "has-a" relationship (composition)

### Polymorphism

- Polymorphism means "many forms", and it occurs when we have many classes that are related to each other by inheritance.
- Inheritance lets us inherit attributes and methods from another class. Polymorphism uses those methods to perform different tasks. This allows us to perform a single action in different ways.
- Overloading
  - Methods of the same name can co-exist if the signatures (number, type, and order of arguments) are different
  - Overloading allows different methods to have the same name, but different signatures where the signature can differ by the number of input parameters or type of input parameters or both.
- Overriding
  - Overriding is a feature that allows a subclass or child class to provide a specific implementation of a method that is already provided by one of its super-classes or parent classes.

#### Runtime type (Dynamic type) vs Compile time type (Static type)

- Runtime vs Compile time
  - Compile-time is the time at which the source code is converted into an executable code while the run time is the time at which the executable code is started running.
  - The compile-time errors can be:
    - Syntax errors
    - Semantic errors
    - Typechecking errors
  - The run-time errors can be:
    - Division by zero
    - Dereferencing a null pointer
    - Running out of memory
    - etc.
- Static and dynamic binding
  - reference: <https://beginnersbook.com/2013/04/java-static-dynamic-binding/>
  - a variable’s compile-type is fixed at compile time, while its run-time type may vary as the program runs
  - Static Binding/early binding
    - happens at *compile time*
    - The binding which can be resolved at compile time by compiler is known as static or early binding. The binding of `static`, `private` and `final` methods is compile-time.  The reason is that the these method cannot be overridden and the type of the class is determined at the compile time.
  - dynamic Binding/late binding
    - happens at *runtime*
    - *Method Overriding* is dynamic bindind which is determined at runtime by the type of object thus late binding happens.
  
  - Example: `X xy = new Y();`
    - Runtime(Dynamic) type is `Y`
    - Compile time(Static) type is `X`
  - Always remember that methods to invoke are determine by runtime type.
  - Methods can be restricted based on the compile time type.
  - Since `static` `private` `final` methods can not be overridden by child class, the methods to invoke are determined by compile time type.

## Interfaces

- an interface can have methods and variables, but the methods declared in an interface are by default abstract (only method signature, no body).
- A class that implements an interface must implement all the methods declared in the interface.
- Interfaces specify what a class must do and not how. It is the blueprint of the class.
- Even though a class can only inherit from one parent class, a class can implement multiple interface
- Just like abstract classes, interfaces cannot be instantiated
- Why do we use interface ?
  - It is used to achieve total abstraction.
  - Since java does not support multiple inheritance in case of class, but by using interface it can achieve multiple inheritance.
  - It is also used to achieve loose coupling.

## Generics

- Generics means parameterized types. The idea is to allow type (Integer, String, … etc, and user-defined types) to be a parameter to methods, classes, and interfaces. Using Generics, it is possible to create classes that work with different data types.
- Why Generics?
  - Type safe:
    - Object is the superclass of all other classes and Object reference can refer to any type object. These features lack type safety. Generics adds that type safety feature. Generics make errors to appear compile time than at run time (It’s always better to know problems in your code at compile time rather than making your code fail at run time).
  - Code Reuse:
    - We can write a method/class/interface once and use for any type we want.

- Generics Class
- Generics Method
- Generics work only with Reference Types. Cannot use primitive data types like int,char..

- Wildcard
  - Upper-bounded wildcard: ? extends Burger
    - any type that is the sub class of Burger, including itself
  - Lower-bounded wildcard: ? super Burger
    - any type that is the super class of Burger, including itself
- Type Erasure
  - Complie time
  - Type erasure can be explained as the process of enforcing type constraints only at compile time and discarding the element type information at runtime.
  - Replace all type parameters in generic types with their bounds or Object if the type parameters are unbounded. The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
  - In general, the compiled generic code actually just uses java.lang.Object wherever you talk about T (or some other type parameter)
- Type Inference
  - Complie time
  - Type inference is a feature of Java which provides ability to compiler to look at each method invocation and corresponding declaration to determine the type of arguments.
  - `List<Integer> list = new ArrayList<Integer>();`
- Type Checking
  - Runtime
  - checking if the value matched the type of the variable it is assigned to
- Late Binding(Dynamic binding)
  - Runtime
  - determine which instance method to call depending on the type of a reference object.
- Type Casting
  - happens during complie time but is checked during runtime
  - convert the type of one variable to another
  - `int myInt = 9;`
    `double myDouble = myInt; // Automatic casting: int to double`
- Accessibility checking
  - run time and compile time
  - checking if a class has an access to field in another class

## Exceptions and Assertions

- we should not throw exceptions that reveal internal implementation of a class as much as possible

## Functional Interfaces

- It is an interface that has a single abstract method (SAM), which must be overridden by the user.

- Examples
  - Comparator<T>: int compare(T x, T y)
    - Compares its two arguments for order.
  - Predicate<T>: boolean test(T x)
    - Evaluates this predicate on the given argument.
  - Function<T,R>: R apply(T x)
    - Applies this function to the given argument.
  - Supplier<T>: T get()
    - Gets a result.
  - Consumer<T>: void accept(T x)
    - Performs this operation on the given argument.

  - BiFunction<T,​U,​R>: R apply(T x)
    - represents a function that accepts two arguments and produces a result.
    - BinaryOperator<T>: R apply(T x)
      - subinterface of BiFunction
      - It takes in two operands of the *same type*, producing a result of the *same type* as the operands.
  
  - Optional
    - An optional instance can have either of the two states — it can have a reference to an instance of type T or empty.
    - factory method
      - Optional.empty();
      - Optional.of(T value);
      - Optional.ofNullable(T value);  
        - This method returns an empty Optional instance if the reference contains a null value.
    - check
      - boolean isEmpty();
      - boolean isPresent();
      - void isPresent(Consumer);
      - void isPresentOrElse(Consumer, Runnable);
        - The Consumer instance is executed if the Optional instance contains some value, otherwise Runnable instance is executed.
    - access optional
      - T get();
      - Optional<T> or(Supplier)
      - T orElse(T else);
      - T orElseGet(Supplier);
      - T orElseThrow();
    - transform optional value
      - <U> Optional<U> map(Function);
      - <U> Optional<U> faltmap(Function);
        - Use map() if the function returns the object you need or flatMap() if the function returns an Optional.
    - stream();
      - transform an Optional instance to a Strea,  otherwise returns an empty Stream

  ```java
        Optional<String> intStr = Optional.of("23");
        Optional<Integer> intValue = intStr.map(Integer::parseInt);

        // Optional wrapper over another Optional wrapper holding result
        Optional<Optional<Integer>> intValue2 = intStr.map(str -> Optional.of(Integer.parseInt(str)));
        // using flatMap() will unwrap the Optional
        Optional<Integer> intValue3 = intStr.flatMap(str -> Optional.of(Integer.parseInt(str)));

        System.out.println(intValue);  // Optional[23]
        System.out.println(intValue2); // Optional[Optional[23]]
        System.out.println(intValue3); // Optional[23]

        List<Integer> listOfIntegers = Arrays.asList("5", "10", "15", "20").stream().map(Integer::parseInt)
                .collect(Collectors.toList());

        // using flatMap() when working with a stream of streams
        List<String> listOfIntegers2 = Arrays.asList(Arrays.asList("5", "10"), Arrays.asList("15", "20")).stream()
                .flatMap(List::stream).collect(Collectors.toList());

        System.out.println(listOfIntegers);  // [5, 10, 15, 20]
        System.out.println(listOfIntegers2); // [5, 10, 15, 20]
    java```

- Functor and Monad
  - a parameterised type such as Optional<String> has a type parameter: String and a wrapper type: Optional. In this case we say that “a String type is wrapped by an Optional context”.
  - Functor provide a constructor and a `map` method
    - exmaples:
      - Optional<T>
      - Stream<T>
      - ArrayList<T>
      - Function<T,U>
  - Monad provide a constructor and a `faltmap` method
    - reference
      - <https://medium.com/@afcastano/monads-for-java-developers-part-1-the-optional-monad-aa6e797b8a6e>
      - <https://medium.com/@afcastano/monads-for-java-developers-part-2-the-result-and-log-monads-a9ecc0f231bb>
    - exmaples:
      - Stream<T>
      - Optional<T>
    - `faltmap()`
      - unwrapping the parameter from the Optional context or some other parametrised types
      - it will unwrap the parameter for us and no need to check it present or not
      - if the Optional is empty, the result will return Optional.empty

    ```java
    public static <T, U, R> Optional<R> flatMap2(Optional<T> opt1, Optional<U> opt2,
   BiFunction<T, U, Optional<R>> mapper) {
    return opt1.flatMap(a -> opt2.flatMap(b -> mapper.apply(a, b)));

   }

   public Optional<Integer> addOptionals(Optional<Integer> opt1, Optional<Integer> opt2) {
      return flatMap2(opt1, opt2, (param1, param2) -> Optional.of(param1 + param2));
    }

    java```

## Stream

- A stream pipeline starts with a data source
- intermediate operations specify tasks to perform
- Terminal operation initiates the processing of a stream pipeline so as to produce a result
- Intermediate operations use lazy evaluation
    – does not perform any operations on stream’s elements until a terminal operation is called
- Stateless and stateful operations
  - Stateless operations do not keep data around (do not maintain state) while moving from processing from one stream element to the next.
    - filter(), map(), faltMap() ...
  - Stateful operations may pass the state from previously precoessed elements to the processing of the next element
    - distinct(), sorted(), limit(), skip() ...

- Parallel Stream
  - parallel()
    - The parallel() intermediate operation turns on a boolean flag that switches the stream pipeline to be parallel
  - Side effect
    - A side effect is an action taken from a stream operation which changes something externally.
    - Stateful operations like forEach(), sorted() will cause side effect. Stateful operations should entirely be avoided
    - If the behavioral parameters do have side-effects, unless explicitly stated, there are no guarantees as to the visibility of those side-effects to other threads, nor are there any guarantees that different operations on the "same" element within the same stream pipeline are executed in the same thread
  - Encounter order
    - In parallel pipeline, the operation peek() and forEach() do not respect encounter order. They are also non-deterministic (producing different results on multiple executions). We should only apply side-effects from these operations if we don't care about the order. If we do care about the order then we have only one option: `forEachOrdered()`.
    - If you want to produce the output in the order of input, use `forEachOrdered` instead of `forEach`, we will lose some benefits of parallelization because of this.
  - To ensure correct parallel execution, stream operations
    - must not interfere with stream data (true for sequential streams also)
      - Would cause ConcurrentModificationException to be thrown.
    - preferably stateless with no side effects
  - Parallelizable `reduce()`
    - To do parallel reduction to a different result type, we need to use this 3 arguments `reduce()`
      - `<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)`
      - accumulator accumulates T elements to intermediate U values
      - combiner combines the intermediate U values into a single U result
      - If we aren't switching types, it turns out that the accumulator function is the same as the combiner function. That's why reduction to the same type has only the accumulator function and reduction to a different type requires separate accumulator and combiner functions. `T reduce​(T identity, BinaryOperator<T> accumulator)`
    - In order to allow us to run reduce in parallel, however, there are several rules that the identity, the accumulator and the combiner must follow:
      - combiner.apply(identity, i) must be equal to i.
      - The combiner and the accumulator must be associative -- the order of applying must not matter.
      - The combiner and the accumulator must be compatible -- `combiner.apply(u, accumulator.apply(identity, t))` must equal to `accumulator.apply(u, t)`
    - reference: <https://stackoverflow.com/questions/24308146/why-is-a-combiner-needed-for-reduce-method-that-converts-type-in-java-8>

## Lazy Evaluation

- Lazy Evaluation
  - Lazy evaluation is an evaluation strategy which holds the evaluation of an expression until its value is needed. It avoids repeated evaluation.
  - For lazy evaluation to be efficient, it needs to use *memoization* to store the key anf value
  - Infinite List
    - In a regular compiler, the concept of an infinite list doesn’t really exist, since it would crash the program. The compiler eagerly evaluates so it will try to evaluate all of the infinite list, eventually running out of memory.
    - However, a lazy complier evaluates what it absolutely needs to. Even though you defined an infinite list, it won’t bother with evaluating all of it.
    - Why even define the list if we can't use it? We can use parts of it.
  - Advantages
    - It allows the language runtime to discard sub-expressions that are not directly linked to the final result of the expression.
    - It reduces the time complexity of an algorithm by discarding the temporary computations and conditionals.
    - It allows the programmer to access components of data structures out-of-order after initializing them, as long as they are free from any circular dependencies.
    - It is best suited for loading data which will be infrequently accessed.
  - Disadvantages
    - It forces the language runtime to hold the evaluation of sub-expressions until it is required in the final result by creating *thunks* (delayed objects).
    - Sometimes it increases space complexity of an algorithm.
    - It is very difficult to find its performance because it contains thunks of expressions before their execution.

## Asynchronous Programming

- `CompletableFuture`
  - A CompletableFuture<V> object returns a value of type V when it completes.
    - `CompletableFuture<Matrix> future = CompletableFuture.supplyAsync(() -> m1.multiply(m2));`
    - `future.thenAccept(System.out::println);`
    - OR `CompletableFuture.supplyAsync(() -> m1.multiply(m2)).thenAccept(System.out::println);`
  - Waiting for completion
    - If you want your code to block until a CompletableFuture completes, you can call join().
      - `m = future.join();`
    - Suppose you have several CompletableFuture objects, say cf1, cf2, and cf3, and you want to block until all of these CompletableFuture completes. You can create a composite CompletableFuture objects, using allOf():
      - `CompletableFuture.allOf(cf1, cf2, cf3).join();`
    - There is also a anyOf, for cases where it is sufficient for any one of the CompletableFuture to complete:
      - `CompletableFuture.anyOf(cf1, cf2, cf3).join();`
  - CompletableFuture is a Functor / Monad
    - Functor
      - `<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> func)`
      - `<U,​V> CompletableFuture<V> thenCombine​(CompletableFuture<? extends U> other, BiFunction<? super T,​? super U,​? extends V> fn)`
      - etc
    - CompletableFuture is a monad too! The thenCompose method is analougous to the flatMap method of Stream and Optional.
      - This also means that CompletableFuture satisfies the monad laws, one of which is that there is a method to wrap a value around with a CompletableFuture. We call this the of method in the context of Stream and Optional, but in CompletableFuture, it is called completedFuture. This method creates a CompletableFuture that is completed.
        - `CompletableFuture.completedFuture(0);`  
