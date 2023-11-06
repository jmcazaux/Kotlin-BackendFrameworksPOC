package com.ironbird.learningspring.data

import org.springframework.data.repository.CrudRepository

interface GuestRepository : CrudRepository<Guest, Long>