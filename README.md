# nshaops-clamavclient

This is a client tester library for CLAMAV written in groovy

This documentation will be upgraded in line with the branch / version specified.


### Pull latest ClamAV server from dockerhub.
https://hub.docker.com/r/mkodockx/docker-clamav/

```bash
docker run -d -p 3310:3310 mkodockx/docker-clamav
```

### Test ClamAV server with a sample document.

```bash
groovy clamAV_tester.groovy localhost 3310 C:\\nshaops\\test\\directory\\doc.pdf
```