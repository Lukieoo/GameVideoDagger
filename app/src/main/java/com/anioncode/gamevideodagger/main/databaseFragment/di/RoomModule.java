//package com.anioncode.gamevideodagger.main.databaseFragment.di;
//
//import android.app.Application;
//
//import androidx.room.Room;
//
//import com.anioncode.gamevideodagger.main.databaseFragment.data.ProductDao;
//import com.anioncode.gamevideodagger.main.databaseFragment.repository.DemoDatabase;
//import com.anioncode.gamevideodagger.main.databaseFragment.repository.ProductDataSource;
//import com.anioncode.gamevideodagger.main.databaseFragment.repository.ProductRepository;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class RoomModule {
//
//    public DemoDatabase demoDatabase;
//
//    public RoomModule(Application mApplication) {
//        demoDatabase = Room.databaseBuilder(mApplication, DemoDatabase.class, "demo-db").build();
//    }
//
//    @Singleton
//    @Provides
//    DemoDatabase providesRoomDatabase() {
//        return demoDatabase;
//    }
//
//    @Singleton
//    @Provides
//    ProductDao providesProductDao(DemoDatabase demoDatabase) {
//        return demoDatabase.getProductDao();
//    }
//
//    @Singleton
//    @Provides
//    ProductRepository productRepository(ProductDao productDao) {
//        return new ProductDataSource(productDao);
//    }
//
//}
