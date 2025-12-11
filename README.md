
# **Mockito – Professional Cheat-Sheet**

A concise reference for engineering teams building clean, isolated, and expressive unit tests using **Mockito** on top of **JUnit 5**.

---

# **1. Core Purpose of Mockito**

Mockito is used to **replace real dependencies with test doubles** so that logic can be tested in isolation.
Mocks remove:

* External calls
* Slow operations
* Non-deterministic behavior
* Unwanted side effects

Tests become faster, cleaner, and focused on **behavior**.

---

# **2. Stubs vs Mocks (Core Concepts)**

### **Stubs**

Return predefined values.
Good for simple scenarios, but become hard to maintain when behavior grows.

Example:

```java
when(repository.getCount()).thenReturn(5);
```

### **Mocks**

Simulate real objects, record interactions, validate behavior, and support dynamic behavior.

Mockito focuses on mocks because they allow:

* flexible responses
* behavior verification
* argument matching
* conditional stubbing

---

# **3. Project Setup (Maven)**

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.x.x</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.x.x</version>
    <scope>test</scope>
</dependency>
```

Add to test class:

```java
@ExtendWith(MockitoExtension.class)
```

---

# **4. Creating Mocks**

### **1. Using Mockito.mock()**

```java
List<String> list = Mockito.mock(List.class);
```

### **2. Using @Mock**

Cleaner and recommended:

```java
@Mock
List<String> list;
```

### **3. Injecting dependencies: @InjectMocks**

Automatically injects mock fields into the tested class.

```java
@Mock
UserRepository repo;

@InjectMocks
UserService service;
```

Mockito uses constructor injection > field injection > setter injection.

---

# **5. Stubbing Behavior**

## **Basic return values**

```java
when(list.size()).thenReturn(10);
```

## **Multiple returns**

```java
when(list.size()).thenReturn(10).thenReturn(20);
```

## **Argument-based stubbing**

```java
when(list.get(anyInt())).thenReturn("Hello");
```

## **Throwing Exceptions**

```java
when(service.process()).thenThrow(new RuntimeException("Error"));
```

## **Void methods**

```java
doNothing().when(logger).log(anyString());
doThrow(new IllegalStateException()).when(service).run();
```

---

# **6. Behavior Verification**

Mockito allows verifying **what interactions happened**.

```java
verify(repo).save(any());
verify(repo, times(2)).deleteById(1L);
verify(repo, never()).findAll();
verify(repo, atLeastOnce()).findByName("Bob");
```

Verification is essential when:

* the method has no return
* business logic depends on calling other components
* side effects matter

---

# **7. Argument Matchers**

### **Built-in matchers**

* `anyInt()`
* `anyString()`
* `any()`
* `eq(value)`
* `nullable(String.class)`

Example:

```java
verify(repo).update(eq(5), anyString());
```

### **Custom ArgumentCaptor**

Useful when needing to inspect passed values.

```java
@Captor
ArgumentCaptor<User> captor;

verify(repo).save(captor.capture());
assertEquals("John", captor.getValue().getName());
```

---

# **8. Mocking Interfaces, Collections & Framework Objects**

Mockito can mock:

* **interfaces**
* **abstract classes**
* **List, Map, Set**
* **Spring components**
* **Repositories / Services / DAOs**

Example: mocking `List`

```java
List<String> mockList = mock(List.class);
when(mockList.get(0)).thenReturn("A");
```

---

# **9. Test Structure & Best Practices**

### ✔ Isolate the class under test

Never mix real dependencies with mocks.

### ✔ Use clear method names

```
shouldReturnFilteredResults()
shouldCallRepositoryOnce()
```

### ✔ Test behavior + state

Combine `assertEquals` with `verify()` when needed.

### ✔ Keep the "Arrange → Act → Assert" structure

### ✔ Do not over-mock

Prefer real objects when possible (e.g., simple DTOs, POJOs).

---

# **10. Typical Mockito Test Lifecycle (Generic Reference)**

A standard progression when building a Mockito-based test suite:

### **✔ Start with simple stubs**

Returning predefined values to break dependencies.

### **✔ Introduce mocks for flexibility**

Mocking services, repositories, and APIs.

### **✔ Add Mockito annotations**

Using `@Mock`, `@InjectMocks`, `@Captor`, `@ExtendWith(MockitoExtension.class)`.

### **✔ Begin verifying behavior**

Checking method calls, arguments, and frequencies.

### **✔ Mock collections and interfaces**

Exploring flexible behavior with List, Map, and custom interfaces.

### **✔ Mature: use argument captors, conditional stubbing, exception mocking**

Achieving full control over tested logic with clean and maintainable tests.

---

# **11. Minimal Mockito Test Template**

```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderService service;

    @Test
    void shouldPlaceOrderSuccessfully() {
        Order order = new Order("Book");

        when(repository.save(any())).thenReturn(order);

        Order result = service.place(order);

        assertEquals("Book", result.getName());
        verify(repository).save(order);
    }
}
```

