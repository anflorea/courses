const koa = require('koa');
const app = new koa();
const server = require('http').createServer(app.callback());
const WebSocket = require('ws');
const wss = new WebSocket.Server({server});
const Router = require('koa-router');
const cors = require('koa-cors');
const bodyParser = require('koa-bodyparser');
const convert = require('koa-convert');

app.use(bodyParser());
app.use(convert(cors()));
app.use(async (ctx, next) => {
  const start = new Date();
  await next();
  const ms = new Date() - start;
  console.log(`${ctx.method} ${ctx.url} ${ctx.response.status} - ${ms}ms`);
});

const getRandomInt = (min, max) => {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min)) + min;
};

const gameSampleNames = ['Traloth', 'Galukoth', 'Zearin', 'Gwylia'];
const gameTypes = ['action', 'adventure', 'board'];
const statusTypes = ['available', 'sold', 'rent'];
const games = [];
for (let i = 0; i < 5; i++) {
  games.push({
    id: i + 1,
    name: gameSampleNames[getRandomInt(0, gameSampleNames.length - 1)] + " " + (i + 1),
    type: gameTypes[getRandomInt(0, gameTypes.length - 1)],
    status: statusTypes[0],
    quantity: getRandomInt(1, 5)
  });
}

const router = new Router();
router.get('/games', ctx => {
  ctx.response.body = games;
  ctx.response.status = 200;
});

router.get('/all', ctx => {
  ctx.response.body = games;
  ctx.response.status = 200;
});


const broadcast = (data) =>
  wss.clients.forEach((client) => {
    if (client.readyState === WebSocket.OPEN) {
      client.send(JSON.stringify(data));
    }
  });


router.post('/addGame', ctx => {
  const headers = ctx.request.body;
  console.log("body: " + JSON.stringify(headers));
  const name = headers.name;
  const type = headers.type;
  if (typeof name !== 'undefined' && typeof type !== 'undefined') {
    const index = games.findIndex(game => game.name === name && game.type === type);
    if (index === -1) {
      let maxId = Math.max.apply(Math, games.map(function (game) {
        return game.id;
      })) + 1;
      let game = {
        id: maxId,
        name,
        type,
        status: statusTypes[0],
        quantity: 1
      };
      games.push(game);
      broadcast(game);
      ctx.response.body = game;
      ctx.response.status = 200;
    } else {
      let game = games[index];
      game.quantity = game.quantity + 1;
      game.status = statusTypes[0];
      ctx.response.body = game;
      ctx.response.status = 200;
    }
  } else {
    ctx.response.body = {text: 'Missing name or type'};
    ctx.response.status = 404;
  }
});

router.post('/removeGame', ctx => {
  const headers = ctx.request.body;
  console.log("body: " + JSON.stringify(headers));
  const id = headers.id;
  if (typeof id !== 'undefined') {
    const index = games.findIndex(game => game.id == id);
    if (index === -1) {
      ctx.response.body = {text: 'No such game'};
      ctx.response.status = 404;
    }
    let game = games[index];
    games.splice(index, 1);
    ctx.response.body = game;
    ctx.response.status = 200;
  } else {
    ctx.response.body = {text: 'Missing name or type'};
    ctx.response.status = 404;
  }
});


router.post('/buyGame', ctx => {
  // console.log("ctx: " + JSON.stringify(ctx));
  const headers = ctx.request.body;
  console.log("body: " + JSON.stringify(headers));
  const id = headers.id;
  if (typeof id !== 'undefined') {
    const index = games.findIndex(game => game.id == id);
    if (index === -1) {
      console.log("No game with id: " + id);
      ctx.response.body = {text: 'Invalid id'};
      ctx.response.status = 404;
    } else {
      let game = games[index];
      if (game.status !== statusTypes[0]) {
        ctx.response.body = {text: 'No more games'};
        ctx.response.status = 404;
      } else {
        if (game.quantity < 1) {
          game.status = statusTypes[1];
          ctx.response.body = {text: 'No more games of this type'};
          ctx.response.status = 404;
        } else {
          game.quantity = game.quantity - 1;
          ctx.response.body = game;
          ctx.response.status = 200;
        }
      }
    }
  } else {
    ctx.response.body = {text: 'Id missing'};
    ctx.response.status = 404;
  }
});


router.post('/rentGame', ctx => {
  // console.log("ctx: " + JSON.stringify(ctx));
  const headers = ctx.request.body;
  console.log("body: " + JSON.stringify(headers));
  const id = headers.id;
  if (typeof id !== 'undefined') {
    const index = games.findIndex(game => game.id == id);
    if (index === -1) {
      console.log("No game with id: " + id);
      ctx.response.body = {text: 'Invalid id'};
      ctx.response.status = 404;
    } else {
      let game = games[index];
      if (game.status !== statusTypes[0]) {
        ctx.response.body = {text: 'No more games'};
        ctx.response.status = 404;
      } else {
        if (game.quantity < 1) {
          game.status = statusTypes[1];
          ctx.response.body = {text: 'No more games of this type'};
          ctx.response.status = 404;
        } else {
          game.quantity = game.quantity - 1;
          ctx.response.body = game;
          ctx.response.status = 200;
        }
      }
    }
  } else {
    ctx.response.body = {text: 'Id missing'};
    ctx.response.status = 404;
  }
});



app.use(router.routes());
app.use(router.allowedMethods());

server.listen(4001);