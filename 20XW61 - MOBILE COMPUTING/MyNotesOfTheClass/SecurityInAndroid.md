# Security in Android

- LLVM
  - IR : bitcode
- GCC
  - IR: gimple
- MUSL
  - can link with libc, clang, libc++

Linux APIs are C. Recently interfaceable in Rust.

No interpreter. No GC. There's an allocator.

**Why do we need IR when the code is already in _Native_?** code optimizations can be done on the IR. Better to do on IR rather than Native.

LLVM can compile Go, C++, Java. All this is possible because All of these compile into a common IR.

IR ?= Intermediate Code Generation

RenderScript with a LLVM frontend. This would then be **interpreted**. Emulating 3D graphics in software.

## Sanitizers

There are three types of sanitizers:

- ASAN
  - GASAN: debug
  - HWASAN: prod
  - SWASAN: debug, parts can be enabled in prod if we have an allocator (Scudo? Scudo Allocation?).
- ISAN: debug
- MSAN: debug
- UBSAN: can be enabled in prod
- LSAN: extension of ASAN. Leaked SAN.

90% of the bugs in Android are due to dealing with memory. (especially use after free bugs, with integer overflow coming in second)

Poison regions in allocators. Guard pages ?
Jumping past the poison space, will not be caught by the Sanitizer. Trying the free the red (unallocated) zones will throw an error.

- use before init
- out of bounds : ArrayIndexOutOfBounds :|
- use after free

C++ avoids GC, because it's faster to do GC on your own. But if there's such an allocator, there's GC anyways.

**How much bytes in a 64-bit machine?**

Resident Set Size. (To prevent memory runs.)

Quarantining the Pages. The time for which this happens is an **Epoch**.

After the quarantine has passed, calling any uninit freed memory, the allocator can't detect this.

### Escape Analysis

```
f() {
    local = int[100]
    return &local;
}
```

The local escapes and the program continues just fine in C++. The compiler optimizes this and puts it into the heap from the stack. C++ may need to stop the world. Android thinks this is frightening to do, so it crashes the application.

STACK AND HEAP. WHAT ARE THEY?
Epoch and Allocators. What are they? How are they different in each domain ?

### Pointer Tagging

if the 16 MSB are set, the allocator is doing something(??).
SWASAN. If the instruction set itself can support pointer tagging, then it is HWASAN. CPU does the job in this case, which is much faster than GPU.

    0001 0000 **\_\_\_** : 8 bits, 48 bits.

The first 8 bits points to 8, 8 byte allocations. 0 means allocated and in use. 1 means that it is free. So we are using bits to identify what's free and what's not.

Any process can flip the bits, so to prevent this, there is a shadow region. If there's clash, the program crashes. So, for every 8 byte we allocate, we need 1 bit in the shadow region. This is why SWASAN isn't used in prod, and only debug.
This is in some eXecuteOnlyMemory (XOM). So, only CPU can RWX.

How the heap and the stack works...

Heap at the top, stack at the bottom. The shadow region exists between two `mprotect` called regions(p). This is like the poison regions. This case the program crashes. Not there.

HWASAN - All processes have the shadow region in one portion.
SWASAN - Every process has the shadow region within it.

HWASAN is cheap but expensive. But it is enabled on Pixels.

**Sanitizers** are work of the Chrome team <3.
Can yt `sanitizers 2013`.

**GWPASAN** -
GWP will provide addr sanity. This is like JIT, in how it looks for **_hot_** threads.

- ADLR?
- Guard page access will crash the program.

All the sanitizers work by **injecting** code into the program.
This injecting happens by IR.

Thread sanitizer - There are 4 shadow regions for every allocation. The RAM usage will be 5x. Thus, it's only used in debug.

It has 16-bit TID, 42-bit epoch, 5-bit addr, Write/Read.
Like : T1, E1, 0-4, W.

This epoch tick based on LKMM. Every language has it's own memory model, hence enabling it do it's thing(??).

Let's say there're two threads.
Like : T1, E1, 0-4, W.
And : T2, E2, 6-8, R.

And another thread joins in,
Like: T3, E3, 3-8, R.

This is okay only if E1 < E3.

The CPU will be able to see it, provided they run on the same CPU core. So, how do we guarantee this? With mutexes. Volatile k/w. Memory barriers. The way the programming languages expose this is called the MemoryModel (MM).

Memory models guarantee **_x happens before/after y_**.
These relations are called,

- happens-before
- happens-after

END OF FIRST DAY CLASS, WE'LL BE LOOKING AT MORE SANS.

---
