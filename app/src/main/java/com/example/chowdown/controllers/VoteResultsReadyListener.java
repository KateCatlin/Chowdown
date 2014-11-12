package com.example.chowdown.controllers;

import com.google.common.collect.Multimap;

/**
 * Created by mattlauer on 2014-11-12.
 */
public interface VoteResultsReadyListener {
    public void voteResultsAreReady(Multimap<String, String> voteResultsReadyMultimap);
}
