# Surface Flinger

Cycles (VSYNC) <- HWC 60Hz - 16.6ms.

Three things happen every VSYNC:

- App starts rendering its layout.
- SurfaceFlinger starts composition various layers.
- Hardware Composer (HWC) starts displaying various bits of the screen.

App -> SurfaceFlinger -> HWC -> Display

**Where does GPU come into play?**
Between App and SurfaceFlinger. It may also be used by SurfaceFlinger to do some composition.

**What is Software rendering and what is Hardware rendering?** Software rendering is when the CPU does the rendering. Hardware rendering is when the GPU does the rendering.

GPU texture like DirectX or OpenGL. Writing in OpenGL, is crossplatform. Then there's VULKAN, which is a new API that is crossplatform. It's a low level API, so it's more efficient. It's also more complicated to use.

Window feeds the view hierarchy to SurfaceFlinger. SurfaceFlinger then feeds the view hierarchy to HWC. HWC then feeds the view hierarchy to the display.

App renders its content thru the UI thread. UI thread draws the content to the Surface. SurfaceFlinger then reads the content from the Surface and composes it with other Surfaces. HWC then displays the content.

This means that the memory usage is going to high. So we use a p-buffer. (A p-buffer is a buffer that is not visible to the user. It's a buffer that is used for offscreen rendering. It's a buffer that is used for rendering. It's a buffer that is used for composition.) If all the three act on the buffer on the same time, there's screen tearing.

## **3-buffering**

App has a buffer Buf1.
SurfaceFlinger has a buffer Buf2.
HWC has a buffer Buf3.

There is this Producer-consumer chain between all the three stages. There is no stalling, so there is no screen tearing.

The surfaceflinger when it sends the buffer to the HWC, it only sends the parts of the screen that has been changed. Let's say we have a website open in the browser. The surfaceflinger sends the parts of the screen that has been changed to the HWC.

HWC directly interacts with the display driver.

If there are multiple apps and they also have their own buffers, there may be mutiple UI threads running. But this is not a problem. There is this **clipping** that happens by the SurfaceFlinger, or the SF can tell it to the HWC to do it. It can either be done on the GPU or the CPU. If the GPU is capable of doing it, ...

**What do we do when there is an external display?** SF creates another buffer for the external display. It becomes the consumer of the HWC. (SF summons the MediaEncoder ? What happens then ?) This all enables Android to have mutiple displays and avoid screen tearing and so on.

Every app has a surface, every SF has a view, every GPU has a Texture EGL, every HWC has a D...

**What is rendering?** Paint(style) layout. 2D commands. 3D commands (used to be done by PixelFlinger.)

Surface Flinger - e-linux.org

# Coroutines

**What is a coroutine?** A coroutine is a function that can
suspend its execution and resume it later. It can be paused and resumed.

**What will be the disadvantages if we don't use co-routines?**

Kotlin Coroutines -> Dispatchers, Immediate (Same thread as caller) , Main (1 thread), IO (n threads).

Work Stealing Scheduler.

**What is the difference between threads and coroutines? How are coroutines used on threads?**

Cloning the process using the clone() system call creates a new process but without the stack pointer. Forking gives us a new process, cloning gives us a new thread. Coroutines are called lightweight, on Golang it's called goroutines.

Coroutines deprecated AsyncTask.

Everytime you create a coroutine, it creates an new job (Context). You can cancel the job. You can also use the job to check if the coroutine is still running. Coroutine can be created in two ways: launch and async. Launch is used when you don't need to return anything. Async is used when you need to return something. After the call of launch, the thread is immediately unblocked. In async, we can async.wait() till the async completes and then the main thread continues with the execution. The code runs like it reads. The context can have a name for the thread. Context totally has the Job, name, and the thread.

A coroutine can only be called from another coroutines.
This is called **Structured Concurrency**. Scala, Swift and Kotlin all have this. This is the most important leap forward in concurrency.
