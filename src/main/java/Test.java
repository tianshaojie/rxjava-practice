import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static Scheduler getNamedScheduler(String name) {
        return Schedulers.from(Executors.newCachedThreadPool(r -> new Thread(r, name)));
    }

    public static void threadInfo(String caller) {
        System.out.println(caller + " => " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
//        Flowable.just("Hello World").subscribe(System.out::println);
//
//        Flowable.just("H2").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.println("s = [" + s + "]");
//            }
//        });
//
//        Flowable.just("H3").subscribe(new Subscriber<String>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                System.out.println("s = [" + s + "]");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("1s = [" + s + "]");
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println("t = [" + t + "]");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("complete");
//            }
//        });
//
//        Flowable.range(1, 10)
//                .parallel()
//                .runOn(Schedulers.computation())
//                .map(v -> v * v)
//                .sequential()
//                .blockingSubscribe(System.out::println);


//        // 1. 初始化被观察者 Observable
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                System.out.println("emitter = [" + Thread.currentThread().getName() + "]");
//                emitter.onNext("h1");
//                emitter.onNext("h2");
//                emitter.onNext("h3");
//            }
//        });
//        // 2. 创建观察者 Observer
//        Observer<String> observer = new Observer<String>() {
//
//            Disposable disposable;
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("onSubscribe = [" + Thread.currentThread().getName() + "]");
//                disposable = d;
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("onNext = [" + Thread.currentThread().getName() + "]");
//                System.out.println("s = [" + s + "]");
//                if(s.equals("h2")) {
//                    disposable.dispose();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        };
//        // 3. 订阅
//        observable.subscribe(observer);
//        System.out.println("--------------------");


//        // 简化版的订阅方式：Consumer
//        Disposable disposable = observable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.println("s = [" + s + "]");
//            }
//        });
//        disposable.dispose(); // ??????
//        System.out.println("--------------------");


//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                System.out.println("emitter = [" + Thread.currentThread().getName() + "]");
//                emitter.onNext("a1");
//                emitter.onNext("a2");
//                emitter.onNext("a3");
//            }
//        })
//                .observeOn(Schedulers.newThread())
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println("accept = [" + Thread.currentThread().getName() + "]");
//                        System.out.println("accept s = [" + s + "]");
//                    }
//                })
//                .observeOn(Schedulers.newThread())
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println("accept1 = [" + Thread.currentThread().getName() + "]");
//                        System.out.println("accept1 s = [" + s + "]");
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println("accept2 = [" + Thread.currentThread().getName() + "]");
//                        System.out.println("accept2 s = [" + s + "]");
//                    }
//                });

//        new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("onSubscribe");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("onNext = [" + Thread.currentThread().getName() + "]");
//                System.out.println("s = [" + s + "]");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("e = [" + e + "]");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        }


//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                System.out.println("Observable thread is : " + Thread.currentThread().getName());
//                e.onNext(1);
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        System.out.println("After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println("After observeOn(io)，Current thread is " + Thread.currentThread().getName());
//                    }
//                });


//https://www.jianshu.com/p/59c3d6bb6a6b
//        Observable
//                .just(0)
//                .observeOn(Schedulers.computation())
//                .map(i -> i + 1)
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(System.out::println);

//        Observable.just("RxJava")
//                .observeOn(getNamedScheduler("map之前的observeOn"))
//                .map(s -> {
//                    threadInfo(".map()-1");
//                    return s + "-map1";
//                })
//                .map( s -> {
//                    threadInfo(".map()-2");
//                    return s + "-map2";
//                })
//                .observeOn(getNamedScheduler("subscribe之前的observeOn"))
//                .subscribe(s -> {
//                    threadInfo(".onNext()");
//                    System.out.println(s + "-onNext");
//                });


//        Observable
//                .create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                        threadInfo("OnSubscribe.call()");
//                        emitter.onNext("RxJava");
//                    }
//                })
//                .subscribeOn(getNamedScheduler("create之后的subscribeOn"))
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        threadInfo(".doOnSubscribe()-1");
//                    }
//                })
//                .subscribeOn(getNamedScheduler("doOnSubscribe1之后的subscribeOn"))
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        threadInfo(".doOnSubscribe()-2");
//                    }
//                })
//                .observeOn(getNamedScheduler("subscribe之前的observeOn"))
//                .subscribe(s -> {
//                    threadInfo(".onNext()");
//                    System.out.println(s + "-onNext");
//                });

//
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                System.out.println("thread name = " + Thread.currentThread().getName());
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//            }
//        }).subscribeOn(Schedulers.newThread())
//                .subscribeOn(getNamedScheduler("test"))
//                .observeOn(Schedulers.newThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println("accept = " + integer + " thread name = " + Thread.currentThread().getName());
//                    }
//                });
//
//        // 此处要sleep一下，等待Rx流转完成，否则看不到任何System.out
//        Thread.sleep(2000);



        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                requestFirst(new Callback() {
                    @Override
                    public void onSuccess(String str) {
                        System.out.println("---requestFirst success");
                        emitter.onNext(str);
                    }
                    @Override
                    public void onFailure() {
                        emitter.onError(new Throwable("onFailure1"));
                    }
                });
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return new Observable<String>() {
                    @Override
                    protected void subscribeActual(Observer<? super String> observer) {
                        requestSecond(new Callback() {
                            @Override
                            public void onSuccess(String str) {
                                System.out.println("---requestSecond success");
                                observer.onNext(s + "-" + str);
                            }
                            @Override
                            public void onFailure() {
                                observer.onError(new Throwable("onFailure2"));
                            }
                        });
                    }
                };
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });

//        changeA();
//        Observable.interval(1, TimeUnit.SECONDS)
//                .take(3)
//                .subscribe(new Observer<Long>() {
//                    Disposable disposable;
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable = d;
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        System.out.println("a = " + a);
//                        if(a == 1) {
//                            disposable.dispose();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        Thread.sleep(5000);
    }

    private static int a = 0;
    private static void changeA() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 1;
            System.out.println("--a = " + a);
        }).start();
    }

    private static void requestFirst(Callback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(callback != null) {
                callback.onSuccess("requestFirst");
            }
        }).start();
    }

    private static void requestSecond(Callback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(callback != null) {
                callback.onSuccess("requestSecond");
            }
        }).start();
    }

    private interface Callback {
        void onSuccess(String str);
        void onFailure();
    }
}
