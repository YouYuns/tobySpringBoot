package com.example.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        List<String> autoConfigs = new ArrayList<>();

        //그럼 이 방식에서 어떻게 파일 정보를 읽어올까?
        // load라는 스프링부트가 제공하는 메서드를 봐야되는데
        //META-INF.spring밑에 해당파일을 넣어준다
        for(String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)){
            autoConfigs.add(candidate);
        }


        //리스트에 집어넣은걸 다시 Array로 바꾸는 작업
//        return autoConfigs.toArray(new String[0]);
       //자바8이후에 array로 바꾸는 작업
        return autoConfigs.stream().toArray(String[]::new);


//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

    }
}
