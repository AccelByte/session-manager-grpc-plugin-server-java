// Copyright (c) 2025 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

package net.accelbyte.session.sessionmanager.service;

import io.grpc.stub.StreamObserver;
import com.google.protobuf.Empty;
import com.google.protobuf.Value;

import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import net.accelbyte.sdk.core.AccelByteSDK;
import net.accelbyte.session.manager.*;

import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GRpcService
public class SessionManagerImplementation extends SessionManagerGrpc.SessionManagerImplBase {

    private AccelByteSDK sdk;

    @Autowired
    public SessionManagerImplementation(AccelByteSDK sdk) {
        this.sdk = sdk;

        this.sdk.loginClient();

        log.info("SessionManager service initialized");
    }

    @Override
    public void onSessionCreated(SessionCreatedRequest request,
        StreamObserver<SessionResponse> responseObserver) {
        
        GameSession gameSession = request.getSession();
        gameSession.getSession().getAttributes().getFieldsMap()
            .put("SAMPLE", Value.newBuilder().setStringValue("value from GRPC server").build());

        SessionResponse response = SessionResponse.newBuilder()
            .setSession(gameSession)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void onSessionUpdated(SessionUpdatedRequest request,
        StreamObserver<Empty> responseObserver) {

        log.info("Old game session id: " + request.getSessionOld().getSession().getId());
        log.info("New game session id: " + request.getSessionNew().getSession().getId());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void onSessionDeleted(SessionDeletedRequest request,
        StreamObserver<Empty> responseObserver) {

        log.info("Deleted session id: " + request.getSession().getSession().getId());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void onPartyCreated(PartyCreatedRequest request,
        StreamObserver<PartyResponse> responseObserver) {

        PartySession gameSession = request.getSession();
        log.info("Party session id: " + gameSession.getSession().getId());

        gameSession.getSession().getAttributes().getFieldsMap()
            .put("PARTY_SAMPLE", Value.newBuilder().setStringValue("party value from GRPC server").build());

        PartyResponse response = PartyResponse.newBuilder()
            .setSession(gameSession)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void onPartyUpdated(PartyUpdatedRequest request,
        StreamObserver<Empty> responseObserver) {

        log.info("Old party session id: " + request.getSessionOld().getSession().getId());
        log.info("New party session id: " + request.getSessionNew().getSession().getId());      
        
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void onPartyDeleted(PartyDeletedRequest request,
        StreamObserver<Empty> responseObserver) {

        log.info("Deleted party session id: " + request.getSession().getSession().getId());
        
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}