package calendar.year._2018.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TissueClaims {

    private final List<Claim> claims;

    private final Map<Position, Integer> nbClaimsPerPosition;

    public TissueClaims() {
        this.claims = new ArrayList<>();
        this.nbClaimsPerPosition = IntStream.range(0, 1001)
                .mapToObj(x -> IntStream.range(0, 1001)
                        .mapToObj(y -> new Position(x, y))
                        .collect(Collectors.toList())
                ).flatMap(List::stream)
                .collect(Collectors.toMap(
                        Function.identity(),
                        pos -> 0
                ));
    }

    public void addClaim(Claim claim) {
        // Add the claim to the claims list
        this.claims.add(claim);

        // Update the number of claims made on each position
        claim.getAllClaimPositions().forEach(position ->
                this.nbClaimsPerPosition.replace(position, this.nbClaimsPerPosition.get(position) + 1)
        );
    }

    public int countNbDisputedParts() {
        return (int) nbClaimsPerPosition.values().stream()
                .filter(nbClaims -> nbClaims > 1)
                .count();
    }

    public Claim getUndisputedClaim() {
        return claims.stream()
                .filter(claim -> claim.getAllClaimPositions().stream()
                        .allMatch(position -> 1 == nbClaimsPerPosition.get(position))
                )
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No claim is fully undisputed"));
    }
}
