import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class StreamPractice {
    public static void main(String[] args) {

        /* 1. 생성하기 */
        String[] arr = new String[] { "a", "b", "c" };
        Stream<String> streamFromArr = Arrays.stream(arr);
        Stream<String> streamFromArrPartOfIt = Arrays.stream(arr, 1, 3);
        streamFromArr.forEach(element -> System.out.println("1. 생성하기 - streamFromArr : " + element));
        streamFromArrPartOfIt.forEach(element -> System.out.println("1. 생성하기 - streamFromArrPartOfIt : " + element));

        // 컬렉션 스트림
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> streamCollection = list.stream(); // list .stream으로 리스트로 부터 스트림이 생성된다
        Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
        streamCollection.forEach(element -> System.out.println("컬렉션 스트림 - streamCollection : " + element));
        parallelStream.forEach(data -> System.out.println("컬렉션 스트림 - parallelStream : " + data));
        parallelStream.close();

        // parallelStream이 뭐지? 병렬처리 스트림
        // 병렬 여부 확인
        boolean isParallel = parallelStream.isParallel();

        System.out.println("병열 여부 확인 - isParalle : " + isParallel);

        // 비어 있는 스트림 만드릭
        Stream<String> streamBuilder = Stream.<String>builder()
                .add("Eric").add("Elena").add("Shire Beggins")
                .build();
        streamBuilder.forEach(element -> System.out.println("비어 있는 스트림 만들기 - streamBuilder : " + streamBuilder));

        // generate
        /* 이 때 생성되는 스트림은 크기가 정해져 있지 않고 무한(infinite)하기 때문에 특정 사이즈로 최대 크기를 제한 */
        Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
        generatedStream.forEach(element -> System.out.println("generate - generatedStream : " + element));

        // iterate
        /* 30이 초기 값이고 값이 2씩 증가하는 값 만들기 */
        /* 이 때 생성되는 스트림으 ㄴ크기가 정해져있지 않고 무한하기 때문에 특정사이즈로 최대 크기를 제한 */
        Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5);
        iteratedStream.forEach(element -> System.out.println("iterated - iteratedStream : " + element));

        // 기보 ㄴ타입형 스트림
        IntStream intStream = IntStream.range(1, 5);
        LongStream longStream = LongStream.range(1, 5);

        IntConsumer intConsumer = i -> System.out.println("intPrimitive Consumer : " + i);
        intStream.forEach(intConsumer);
        LongConsumer longConsumer = i -> System.out.println("longPrimitive Consumer : " + i);
        longStream.forEach(longConsumer);

        // boxed
        /*
         * Intstream 같이 우너시 타입에 대한 스트림 지원을 클래스 타입(예: IntStream -> Stream<Integer>)으로 전환
         */
        Stream<Integer> boxedIntStream = IntStream.range(1, 3).boxed();
        boxedIntStream.forEach(data -> System.out.println("boxed - boxedIntStream : " + data));

        // Random 랜덤
        IntStream doubles = new Random().ints(45, 1, 45)
                .distinct()
                .limit(6); // 난수 6개 생성 1 ~ 45 범위
        doubles.forEach(element -> System.out.println("random ints : " + element));

        // 문자열 스트림
        IntStream chars = "Stream".chars();
        chars.forEach(element -> System.out.println("문자열 스트림 charStream : " + element));

        // 정규식 사용
        Stream<String> stringStream = Pattern.compile(", ").splitAsStream("Eric, Elena, Chire");
        stringStream.forEach(element -> System.out.println(" stringStream : " + element));

        // 파일 스트림
        /* IO Exception 처리 필요 */
        Stream<String> lineStream = Stream.<String>builder().build();
        try {
            lineStream = Files.lines(Paths.get("src/main/resources/application.yml"), Charset.forName("UTF-8"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 125
    }

}