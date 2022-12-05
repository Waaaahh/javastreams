import java.util.Arrays;
import java.util.List;
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
    }
}