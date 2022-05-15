package platform;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeRepository {
    private final List<Code> listOfCodeSnippets = Collections.synchronizedList(new ArrayList<>());

    public Code getById(int id) {
        return listOfCodeSnippets.get(id - 1);
    }

    public List<Code> getRecentlyUploaded(int n) {
        return listOfCodeSnippets.stream()
                .sorted(Comparator.comparing(Code::getDateTime).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public int size() {
        return listOfCodeSnippets.size();
    }

    public void add(Code code) {
        listOfCodeSnippets.add(code);
    }
}
