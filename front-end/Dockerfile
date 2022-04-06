FROM node:alpine AS node
WORKDIR /app
COPY . .k
RUN npm ci && npm run build

FROM nginx:alpine
COPY --from=node /app/dist/front-end /usr/share/nginx/html
EXPOSE 80