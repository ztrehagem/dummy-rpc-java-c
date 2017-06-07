#ifndef BLIST_H
#define BLIST_H

#include <stdio.h>
#include <stdlib.h>

struct blist {
  unsigned char *bytes;
  size_t len;
  struct blist *next;
};

struct blist *blist_append(struct blist *tail, const unsigned char *bytes, const size_t len);
void blist_concat(unsigned char *dest, struct blist *list);
size_t blist_recv(int sd, struct blist **list);


#endif /* end of include guard: BLIST_H */