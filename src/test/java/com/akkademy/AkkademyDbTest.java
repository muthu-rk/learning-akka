package com.akkademy;

/**
 * Created by muthu on 30-03-2018.
 */

import static org.junit.Assert.assertEquals;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.akkademy.messages.SetRequest;
import org.junit.Test;

public class AkkademyDbTest {
    ActorSystem system = ActorSystem.create();

    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap() {
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(system, Props.create(AkkademyDb.class));
        actorRef.tell(new SetRequest("key", "value"),ActorRef.noSender());
        AkkademyDb akkademyDb = actorRef.underlyingActor();
        assertEquals(akkademyDb.map.get("key"), "value");
    }

    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap2() {
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(system, Props.create(AkkademyDb.class));

        actorRef.tell(new SetRequest("key1", "value1"),ActorRef.noSender());
        actorRef.tell(new SetRequest("key2", "value2"),ActorRef.noSender());

        AkkademyDb akkademyDb = actorRef.underlyingActor();

        assertEquals(akkademyDb.map.size(), 2);
    }
}