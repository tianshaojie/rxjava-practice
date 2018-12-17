import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * RxJava Http Manager
 * 注意 add 与 cancel 成对调用
 */
public class RxLifeManager {

    /**
     * 针对一个Tag下发生多个请求
     */
    private HashMap<String, List<Disposable>> map;

    private volatile static RxLifeManager instance;

    private RxLifeManager() {
        if(instance != null) {
            throw new RuntimeException("This is not allowed.");
        }
        map = new HashMap<>();
    }

    public static RxLifeManager getInstance() {
        if(instance == null) {
            synchronized(RxLifeManager.class) {
                if(instance == null) {
                    instance = new RxLifeManager();
                }
            }
        }
        return instance;
    }


    public void add(String tag, Disposable disposable) {
        List<Disposable> list = map.get(tag);
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(disposable);
        map.put(tag, list);
    }

    public void cancel(String tag) {
        List<Disposable> list = map.get(tag);
        if(list != null && list.size() > 0) {
            for(Disposable disposable : list) {
                if(disposable != null) {
                    disposable.dispose();
                }
            }
        }
    }

    public void cancelAll() {
        for(String tag : map.keySet()) {
            cancel(tag);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            Thread.sleep(1000);
            emitter.onNext(2);
            Thread.sleep(1000);
            emitter.onNext(3);
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxLifeManager.getInstance().add("main", d);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
                if(integer == 2) {
//                    RxLifeManager.getInstance().cancel("main");
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });



        Thread.sleep(5000);
    }
}
